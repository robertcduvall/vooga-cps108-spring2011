package vooga.stats;

public class StringStat<T> extends AbstractStat{

	public StringStat(T t) {
		super(t);
	}

	@Override
	public T update() {
		return (T) getStat();
	}

}
