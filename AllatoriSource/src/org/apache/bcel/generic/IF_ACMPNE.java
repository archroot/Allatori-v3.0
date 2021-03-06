/* IF_ACMPNE - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package org.apache.bcel.generic;

public class IF_ACMPNE extends IfInstruction {
	private static final long serialVersionUID = -5532275839192747402L;

	IF_ACMPNE() {
		/* empty */
	}

	public IF_ACMPNE(InstructionHandle target) {
		super((short) 166, target);
	}

	@Override
	public IfInstruction negate() {
		return new IF_ACMPEQ(target);
	}

	@Override
	public void accept(Visitor v) {
		v.visitStackConsumer(this);
		v.visitBranchInstruction(this);
		v.visitIfInstruction(this);
		v.visitIF_ACMPNE(this);
	}
}
