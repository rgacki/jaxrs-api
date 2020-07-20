package jakarta.ws.rs.client;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

/**
 * A emitter of events for creating the entity stream of a request.
 *
 * The emitter relies on an open connection to the targeted resource. All events are serialized by an
 * {@link EntityEventWriter}.
 *
 * @author Robert Gacki
 */
public interface EntityEventEmitter {

	/**
	 * Emit an event to the request's entity stream. The event producer is responsible for sending events in a timely
	 * manner to avoid being timed-out by the target.
	 *
	 * @throws ProcessingException in case the event processing or I/O operation fails.
	 * @param event the event to emit
	 */
	void emit(Object event);

	/**
	 * Completes the emission of events and returns the response. Any subsequent emissions will fail.
	 *
	 * @return the response
	 * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
	 * conversion of the response entity data to an instance of a particular Java type).
	 * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
	 * @throws WebApplicationException in case the response status code of the response returned by the server is not
	 * {@link Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
	 * not represent {@link Response}.
	 */
	Response done();

	/**
	 * Completes the emission of events and returns the response. Any subsequent emissions will fail.
	 *
	 * @return the response
	 * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
	 * conversion of the response entity data to an instance of a particular Java type).
	 * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
	 * @throws WebApplicationException in case the response status code of the response returned by the server is not
	 * {@link Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
	 * not represent {@link Response}.
	 */
	<T> T done(Class<T> responseType);

	/**
	 * Completes the emission of events and returns the response. Any subsequent emissions will fail.
	 *
	 * @return the response
	 * @throws ResponseProcessingException in case processing of a received HTTP response fails (e.g. in a filter or during
	 * conversion of the response entity data to an instance of a particular Java type).
	 * @throws ProcessingException in case the request processing or subsequent I/O operation fails.
	 * @throws WebApplicationException in case the response status code of the response returned by the server is not
	 * {@link Response.Status.Family#SUCCESSFUL successful} and the specified generic response type does
	 * not represent {@link Response}.
	 */
	<T> T done(GenericType<T> responseType);

}
