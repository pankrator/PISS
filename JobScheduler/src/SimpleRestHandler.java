import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Method;
import fi.iki.elonen.NanoHTTPD.Response.IStatus;
import fi.iki.elonen.NanoHTTPD.Response.Status;

public class SimpleRestHandler implements EndPoint {

	@Override
	public Object call(IHTTPSession session) {
		return new Result();
	}

	@Override
	public IStatus getStatus() {
		return Status.OK;
	}

	@Override
	public String getMimeType() {
		return "application/json";
	}
	
	@Override
	public Method getMethod() {
		return Method.GET;
	}
}
