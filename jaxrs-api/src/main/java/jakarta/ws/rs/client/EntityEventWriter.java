package jakarta.ws.rs.client;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @param <T> the event type the writer handles
 *
 * @author Robert Gacki
 */
public interface EntityEventWriter<T> {

	/**
	 * Ascertain if the EntityEventWriter supports a particular type.
	 *
	 * @param type        the class of the event instance that is to be written.
	 * @param genericType the type of instance to be written, obtained either by reflection of a resource method return type
	 *                    or via inspection of the returned instance. {@link jakarta.ws.rs.core.GenericEntity} provides a way to specify this
	 *                    information at runtime.
	 * @param annotations an array of the annotations attached to the message entity instance.
	 * @param mediaType   the media type of the HTTP entity.
	 * @return {@code true} if the type is supported, otherwise {@code false}.
	 */
	boolean canEmit(Class<?> type, Type genericType,
					Annotation[] annotations, MediaType mediaType);

	/**
	 * Write a type to an HTTP message. The message header map is mutable but any changes must be made before writing to the
	 * output stream since the headers will be flushed prior to writing the message body.
	 *
	 * @param t            the instance to write.
	 * @param type         the class of instance that is to be written.
	 * @param genericType  the type of instance to be written. {@link jakarta.ws.rs.core.GenericEntity} provides a way to
	 *                     specify this information at runtime.
	 * @param annotations  an array of the annotations attached to the message entity instance.
	 * @param mediaType    the media type of the HTTP entity.
	 * @param httpHeaders  a mutable map of the HTTP message headers.
	 * @param entityStream the {@link OutputStream} for the HTTP entity. The implementation must not close the output
	 *                     stream.
	 * @throws java.io.IOException                   if an IO error arises.
	 * @throws jakarta.ws.rs.WebApplicationException if a specific HTTP error response needs to be produced. Only effective if
	 *                                               thrown prior to the message being committed.
	 */
	void emitTo(T t, Class<?> type, Type genericType, Annotation[] annotations,
				MediaType mediaType,
				MultivaluedMap<String, Object> httpHeaders,
				OutputStream entityStream)
			throws java.io.IOException, jakarta.ws.rs.WebApplicationException;

}
