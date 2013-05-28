package xingu.node.commons.signal;

public class ExceptionSignal
	extends SignalSupport
{
	private Signal		signal;

	private Throwable	error;

	private String		trace;

	public ExceptionSignal()
	{}

	public ExceptionSignal(Signal signal, String trace)
	{
		this.signal = signal;
		this.trace  = trace;
	}

	public ExceptionSignal(Signal signal, Throwable t)
	{
		this.signal = signal;
		this.error  = t;
	}

	public Signal getSignal(){return signal;}
	public void setSignal(Signal signal){this.signal = signal;}
	public Throwable getError(){return error;}
	public void setError(Throwable error){this.error = error;}
	public String getTrace(){return trace;}
	public void setTrace(String trace){this.trace = trace;}
}
