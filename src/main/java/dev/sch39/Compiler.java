package dev.sch39;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: idnlang <sourcefile.id>");
            return;
        }

        String sourceFile = args[0];

        try {
            CharStream inpCharStream = CharStreams.fromFileName(sourceFile);
            IdnLangLexer lexer = new IdnLangLexer(inpCharStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            IdnLangParser parser = new IdnLangParser(tokens);

            ParseTree tree = parser.program();
            IdnlangVisitorImpl visitorImpl = new IdnlangVisitorImpl();
            visitorImpl.visit(tree);
        } catch (IOException e) {
            System.err.println("Error reading file: " + sourceFile);
            e.printStackTrace();
        }
    }

}
