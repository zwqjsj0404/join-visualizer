package org.joinvisualizer;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Hannes John
 * @since 25.05.2010 17:11:13
 */
public class Field {
	private final String dotIdentifier = String.valueOf(hashCode());
	private final Table table;
	private final String name;
	public Field(final Table table, final String name) {
		if(name == null) {
			throw new IllegalArgumentException("Der Name darf nicht null sein.");
		}
		this.table = table;
		this.name = name;
	}
	public boolean is(final Table table, final String name) {
		return this.table == table && this.name.equals(name);
	}
	public boolean isPartOf(final Table table) {
		return this.table == table;
	}
	public void paintDeclaration(final Writer writer) {
		try {
			writer.write("|<" + dotIdentifier + "> " + name + "\\l");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void paintUsage(final Writer writer) {
		try {
			table.paintUsage(writer);
			writer.write(":" + dotIdentifier);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
