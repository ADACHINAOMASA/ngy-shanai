package nis.framework.ejb;


public interface Executable<I, O> {

	// TODO:何とか引数を複数指定可にできないものか

	public O execute(I in);

}
