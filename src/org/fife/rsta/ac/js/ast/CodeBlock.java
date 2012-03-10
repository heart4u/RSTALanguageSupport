/*
 * 02/25/2012
 *
 * Copyright (C) 2012 Robert Futrell
 * robert_futrell at users.sourceforge.net
 * http://fifesoft.com/rsyntaxtextarea
 *
 * This library is distributed under a modified BSD license.  See the included
 * RSTALanguageSupport.License.txt file for details.
 */
package org.fife.rsta.ac.js.ast;

import java.util.ArrayList;
import java.util.List;


/**
 * A block of code. This can be used to implement <em>very</em> simple parsing
 * for languages that have some concept of code blocks, such as C, Perl, Java,
 * etc. Currently, using <code>CodeBlock</code>s provides a means of remembering
 * where variables are defined, as well as their scopes.
 * 
 * @author Robert Futrell
 * @version 1.0
 * @see VariableDeclaration
 */
public class CodeBlock {

	private int start;
	private int end;
	private CodeBlock parent;
	private List children;
	private List varDecs;


	/**
	 * Constructor.
	 * 
	 * @param start The starting offset of the code block.
	 */
	public CodeBlock(int start) {
		this.start = start;
		end = Integer.MAX_VALUE;
	}


	/**
	 * Creates and returns a child (nested) code block.
	 * 
	 * @param start The starting offset of the nested code block.
	 * @return The code block.
	 */
	public CodeBlock addChildCodeBlock(int start) {
		CodeBlock child = new CodeBlock(start);
		child.parent = this;
		if (children == null) {
			children = new ArrayList();
		}
		children.add(child);
		return child;
	}


	/**
	 * Adds a variable declaration.
	 * 
	 * @param varDec The variable declaration.
	 */
	public void addVariable(JSVariableDeclaration varDec) {
		if (varDecs == null) {
			varDecs = new ArrayList();
		}
		varDecs.add(varDec);
	}


	/**
	 * Returns whether this code block contains a given offset.
	 * 
	 * @param offset The offset.
	 * @return Whether this code block contains that offset.
	 */
	public boolean contains(int offset) {
		return offset >= start && offset < end;
	}


	/**
	 * Returns a child code block.
	 * 
	 * @param index The index of the child code block.
	 * @return The child code block.
	 * @see #getChildCodeBlockCount()
	 */
	public CodeBlock getChildCodeBlock(int index) {
		return (CodeBlock) children.get(index);
	}


	/**
	 * Returns the number of child code blocks.
	 * 
	 * @return The child code block count.
	 * @see #getChildCodeBlock(int)
	 */
	public int getChildCodeBlockCount() {
		return children == null ? 0 : children.size();
	}


	/**
	 * Returns the end offset of this code block.
	 * 
	 * @return The end offset.
	 * @see #getStartOffset()
	 * @see #setEndOffset(int)
	 */
	public int getEndOffset() {
		return end;
	}


	/**
	 * Returns the parent code block.
	 * 
	 * @return The parent code block, or <code>null</code> if there isn't one.
	 */
	public CodeBlock getParent() {
		return parent;
	}


	/**
	 * Returns the start offset of this code block.
	 * 
	 * @return The start offset.
	 * @see #getEndOffset()
	 */
	public int getStartOffset() {
		return start;
	}


	/**
	 * Returns a variable declaration.
	 * 
	 * @param index The index of the declaration.
	 * @return The declaration.
	 * @see #getVariableDeclarationCount()
	 */
	public JSVariableDeclaration getVariableDeclaration(int index) {
		return (JSVariableDeclaration) varDecs.get(index);
	}


	/**
	 * Returns the number of variable declarations in this code block.
	 * 
	 * @return The number of variable declarations.
	 * @see #getVariableDeclaration(int)
	 */
	public int getVariableDeclarationCount() {
		return varDecs == null ? 0 : varDecs.size();
	}


	/**
	 * Sets the end offset of this code block.
	 * 
	 * @param end The end offset.
	 * @see #getEndOffset()
	 */
	public void setEndOffset(int end) {
		this.end = end;
	}


}