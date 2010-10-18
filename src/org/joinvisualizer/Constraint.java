package org.joinvisualizer;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Hannes John
 * @since 25.05.2010 17:11:23
 */
public class Constraint {
	private final PaintStrategy paintStrategy;
	public Constraint(final String expression, final Field field1, final Field field2, final String operator) {
		if(expression == null || operator == null) {
			throw new IllegalArgumentException("Der Ausdruck darf nicht null sein.");
		}
		this.paintStrategy = new PaintStrategy() {
			@Override
			public void paint(final Writer writer) {
				try {
					writer.write("\t");
					field1.paintUsage(writer);
					writer.write(" -- ");
					field2.paintUsage(writer);
					writer.write(" [label=\"" + operator + "\"];\n");
				} catch (final IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
	public Constraint(final String expression) {
		if(expression == null) {
			throw new IllegalArgumentException("Der Ausdruck darf nicht null sein.");
		}
		this.paintStrategy = new PaintStrategy() {
			@Override
			public void paint(final Writer writer) {
				try {
					writer.write("\t");
					writer.write("constraint" + hashCode() + " [shape=box, label=\"" + ensureExpression(expression) + "\"];\n");
				} catch (final IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}
	public void paint(final Writer writer) {
		paintStrategy.paint(writer);
	}
	private String ensureExpression(final String expression) {
		return expression.replaceAll("\"", "\"\"");
	}
	private static interface PaintStrategy {
		void paint(final Writer writer);
	}
}
