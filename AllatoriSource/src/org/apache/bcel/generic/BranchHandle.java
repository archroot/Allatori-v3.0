package org.apache.bcel.generic;

public final class BranchHandle extends InstructionHandle {
	private static final long serialVersionUID = -2520878285698255956L;
	private BranchInstruction bi;
	private static BranchHandle bh_list = null;

	private BranchHandle(BranchInstruction i) {
		super(i);
		bi = i;
	}

	static final BranchHandle getBranchHandle(BranchInstruction i) {
		if (bh_list == null)
			return new BranchHandle(i);
		final BranchHandle bh = bh_list;
		bh_list = (BranchHandle) bh.next;
		bh.setInstruction(i);
		return bh;
	}

	@Override
	protected void addHandle() {
		next = bh_list;
		bh_list = this;
	}

	@Override
	public int getPosition() {
		return bi.position;
	}

	@Override
	void setPosition(int pos) {
		i_position = bi.position = pos;
	}

	@Override
	protected int updatePosition(int offset, int max_offset) {
		final int x = bi.updatePosition(offset, max_offset);
		i_position = bi.position;
		return x;
	}

	public void setTarget(InstructionHandle ih) {
		bi.setTarget(ih);
	}

	public void updateTarget(InstructionHandle old_ih, InstructionHandle new_ih) {
		bi.updateTarget(old_ih, new_ih);
	}

	public InstructionHandle getTarget() {
		return bi.getTarget();
	}

	@Override
	public void setInstruction(Instruction i) {
		super.setInstruction(i);
		if (!(i instanceof BranchInstruction))
			throw new ClassGenException(new StringBuilder().append("Assigning ").append(i)
					.append(" to branch handle which is not a branch instruction").toString());
		bi = (BranchInstruction) i;
	}
}
