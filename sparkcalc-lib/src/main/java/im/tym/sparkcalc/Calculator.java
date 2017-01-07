package im.tym.sparkcalc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import im.tym.sparkcalc.function.BinaryFunction;
import im.tym.sparkcalc.function.Functions;
import im.tym.sparkcalc.parse.BaseParser;
import im.tym.sparkcalc.parse.FunctionToken;
import im.tym.sparkcalc.parse.NumberToken;
import im.tym.sparkcalc.parse.Parser;
import im.tym.sparkcalc.parse.Token;

/**
 * @author vit@tym.im
 */
public class Calculator implements Function<String, Number> {
    public static final int NON_REDUCABLE_TOKENS = 4;
    private Parser parser = BaseParser.INSTANCE;
    private Collection<BinaryFunction> functions = Functions.DOUBLE_FUNCTIONS;

    @Override
    public Number apply(String s) {
        Stream<Token> tokenStream = StreamSupport.stream(parser.apply(s), false);
        List<Token> readTokens = new ArrayList<>(NON_REDUCABLE_TOKENS);
        tokenStream.forEachOrdered(t -> {
            Token resolvedToken = resolveToken(t);
            if (resolvedToken instanceof ResolvedFunctionToken && readTokens.size() >= 3) {
                //Try reduce
                ResolvedFunctionToken currentToken = (ResolvedFunctionToken) resolvedToken;
                ResolvedFunctionToken prevToken = (ResolvedFunctionToken) readTokens.get(readTokens.size() - 2);
                if (prevToken.getPriority() >= currentToken.getPriority()) {
                    reduce(readTokens);
                }
            }
            readTokens.add(resolvedToken);
        });
        while (readTokens.size() > 1) {
            reduce(readTokens);
        }
        return ((NumberToken)readTokens.get(0)).getNumber();
    }

    private void reduce(List<Token> readTokens) {
        Number right = ((NumberToken)readTokens.remove(readTokens.size() - 1)).getNumber();
        ResolvedFunctionToken functionToken  = (ResolvedFunctionToken) readTokens.remove(readTokens.size() - 1);
        Number left = ((NumberToken)readTokens.remove(readTokens.size() - 1)).getNumber();
        readTokens.add(new NumberToken(functionToken.getFunction().calculate(functionToken.getToken(), left, right)));
    }

    private Token resolveToken(Token t) {
        if (t instanceof FunctionToken) {
            FunctionToken functionToken = (FunctionToken) t;
            Optional<BinaryFunction> functionOptional =
                    functions.stream().filter(f -> f.accept(functionToken) != BinaryFunction.DONT_PROCESS).findFirst();
            if (functionOptional.isPresent()) {
                BinaryFunction function = functionOptional.get();
                return new ResolvedFunctionToken(function.accept(functionToken), function, functionToken);
            } else {
                throw new IllegalStateException("Unknown function token" + functionToken);
            }
        } else {
            return t;
        }
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Collection<BinaryFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(Collection<BinaryFunction> functions) {
        this.functions = functions;
    }

    private static class ResolvedFunctionToken implements Token{
        private final int priority;
        private final BinaryFunction function;
        private final FunctionToken token;

        public ResolvedFunctionToken(int priority, BinaryFunction function, FunctionToken token) {
            this.priority = priority;
            this.function = function;
            this.token = token;
        }

        public int getPriority() {
            return priority;
        }

        public BinaryFunction getFunction() {
            return function;
        }

        public FunctionToken getToken() {
            return token;
        }
    }
}
