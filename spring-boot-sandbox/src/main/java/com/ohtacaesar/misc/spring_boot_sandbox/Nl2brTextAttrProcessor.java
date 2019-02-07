package com.ohtacaesar.misc.spring_boot_sandbox;


import java.util.Collections;
import java.util.List;
import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.attr.AbstractChildrenModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.unbescape.html.HtmlEscape;

public final class Nl2brTextAttrProcessor extends AbstractChildrenModifierAttrProcessor {

  public static final int ATTR_PRECEDENCE = 1310;
  public static final String ATTR_NAME = "nl2br";

  public Nl2brTextAttrProcessor() {
    super(ATTR_NAME);
  }

  @Override
  protected final List<Node> getModifiedChildren(
      final Arguments arguments, final Element element, final String attributeName) {

    String text = getText(arguments, element, attributeName);
    if (text != null) {
      text = HtmlEscape.escapeHtml4Xml(text).replaceAll("\n", "<br/>");
    }

    final Text newNode = new Text(text == null ? "" : text, null, null, true);

    // Setting this allows avoiding text inliners processing already generated text,
    // which in turn avoids code injection.
    newNode.setProcessable(false);

    return Collections.singletonList(newNode);

  }

  protected final String getText(final Arguments arguments, final Element element,
      final String attributeName) {
    final String attributeValue = element.getAttributeValue(attributeName);

    final Configuration configuration = arguments.getConfiguration();
    final IStandardExpressionParser expressionParser =
        StandardExpressions.getExpressionParser(configuration);

    final IStandardExpression expression = expressionParser
        .parseExpression(configuration, arguments, attributeValue);

    final Object result = expression.execute(configuration, arguments);

    return (result == null ? "" : result.toString());
  }

  @Override
  public int getPrecedence() {
    return ATTR_PRECEDENCE;
  }


}
