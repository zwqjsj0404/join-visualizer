package org.joinvisualizer;

import java.io.IOException;
import java.io.Writer;


/**
 * @author Hannes John
 * @since 25.05.2010 17:10:53
 */
public class Table {
	private final String name;
	private final String alias;
	public Table(final String name, final String alias) {
		if(name == null || alias == null) {
			throw new IllegalArgumentException("Tabellennamen dürfen nicht null sein.");
		}
		this.name = name;
		this.alias = alias;
	}
	public Table(final String name) {
		this(name, name);
	}
	public boolean is(final String alias) {
		if(alias == null) {
			return false;
		}
		return this.alias.equals(alias);
	}
	public void paintHeader(final Writer writer) {
		try {
			writer.write("\t" + ensureAlias(alias) + " [label=\"" + name.toUpperCase() + (alias.equalsIgnoreCase(name) ? "" : (" " + alias)));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void paintFooter(final Writer writer) {
		try {
			writer.write("\"];\n");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void paintUsage(final Writer writer) {
		try {
			writer.write(ensureAlias(alias));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	private String ensureAlias(final String alias) {
		return alias;
	}
}
