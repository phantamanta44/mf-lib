package io.github.phantamanta44.mobafort.lib.collection;

public interface ISelfMutatingCollection<E> extends ISimpleCollection<E> {

	void onSelfMutation(Runnable cb);

}
