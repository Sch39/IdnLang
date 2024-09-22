package dev.sch39;

import java.util.HashMap;
import java.util.Map;

class IdnlangVisitorImpl extends IdnLangBaseVisitor<Object> {
  private final Map<String, Object> variables = new HashMap<>();

  @Override
  public Void visitVariableDeclaration(IdnLangParser.VariableDeclarationContext ctx) {
    String varName = ctx.ID().getText();
    Object value = visit(ctx.expression());
    // Cek tipe data secara dinamis
    if (value instanceof Integer) {
      int intValue = (int) value;
      if (intValue >= Byte.MIN_VALUE && intValue <= Byte.MAX_VALUE) {
        variables.put(varName, (byte) intValue); // Alokasi tipe byte
      } else if (intValue >= Short.MIN_VALUE && intValue <= Short.MAX_VALUE) {
        variables.put(varName, (short) intValue); // Alokasi tipe short
      } else {
        variables.put(varName, intValue); // Alokasi tipe int
      }
    } else {
      variables.put(varName, value); // Alokasikan tipe float atau string sesuai jenisnya
    }
    return null;
  }

  @Override
  public Void visitPrintStatement(IdnLangParser.PrintStatementContext ctx) {
    Object value = visit(ctx.expression());
    System.out.println(value);
    return null;
  }

  @Override
  public Object visitExpression(IdnLangParser.ExpressionContext ctx) {
    if (ctx.getChildCount() == 3) {
      Object left = visit(ctx.getChild(0));
      String operator = ctx.getChild(1).getText();
      Object right = visit(ctx.getChild(2));

      switch (operator) {
        case "*":
          return ((Number) left).doubleValue() * ((Number) right).doubleValue();
        case "/":
          return ((Number) left).doubleValue() / ((Number) right).doubleValue();
        case "%":
          return ((Number) left).intValue() % ((Number) right).intValue();
        case "+":
          return ((Number) left).doubleValue() + ((Number) right).doubleValue();
        case "-":
          return ((Number) left).doubleValue() + ((Number) right).doubleValue();
        default:
          throw new RuntimeException("Error: operator tidak dikenali");
      }
    } else if (ctx.getChildCount() == 1) {
      if (ctx.INT_LITERAL() != null) {
        return Integer.parseInt(ctx.INT_LITERAL().getText());
      } else if (ctx.FLOAT_LITERAL() != null) {
        return Float.parseFloat(ctx.FLOAT_LITERAL().getText());
      } else if (ctx.STRING_LITERAL() != null) {
        return ctx.STRING_LITERAL().getText().replaceAll("\"", "");
      } else if (ctx.ID() != null) {
        return variables.get(ctx.ID().getText());
      }
    } else if (ctx.getChildCount() == 3 && ctx.getChild(0).getText().equals("(")) {
      return visit(ctx.getChild(1));
    }
    return null;
  }

  @Override
  public Void visitAssignment(IdnLangParser.AssignmentContext ctx) {
    String varName = ctx.ID().getText();
    if (!variables.containsKey(varName)) {
      throw new RuntimeException("Error: Variabel " + varName + " belum dideklarasikan");
    }

    Object value = visit(ctx.expression());
    variables.put(varName, value);
    return null;
  }
}