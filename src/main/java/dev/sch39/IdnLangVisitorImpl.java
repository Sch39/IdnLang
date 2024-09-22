package dev.sch39;

import java.util.HashMap;
import java.util.Map;

class IdnlangVisitorImpl extends IdnLangBaseVisitor<Object> {
  private final Map<String, Object> variables = new HashMap<>();

  @Override
  public Void visitVariableDeclaration(IdnLangParser.VariableDeclarationContext ctx) {
    String varName = ctx.ID().getText();
    Object value = visit(ctx.expression());
    variables.put(varName, value);
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
    if (ctx.INT_LITERAL() != null) {
      return Integer.parseInt(ctx.INT_LITERAL().getText());
    } else if (ctx.FLOAT_LITERAL() != null) {
      return Float.parseFloat(ctx.FLOAT_LITERAL().getText());
    } else if (ctx.STRING_LITERAL() != null) {
      return ctx.STRING_LITERAL().getText().replaceAll("\"", "");
    } else if (ctx.ID() != null) {
      return variables.get(ctx.ID().getText());
    }
    return null;
  }
}