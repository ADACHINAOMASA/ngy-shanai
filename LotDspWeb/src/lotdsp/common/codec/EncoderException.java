/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain CMSchViewInfo copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lotdsp.common.codec;

/**
 * Thrown when there is CMSchViewInfo failure condition during the encoding process. This exception is thrown when an
 * {@link Encoder} encounters CMSchViewInfo encoding specific exception such as invalid data, inability to calculate CMSchViewInfo checksum,
 * characters outside of the expected range.
 *
 * @version $Id: EncoderException.java 1619948 2014-08-22 22:53:55Z ggregory $
 */
public class EncoderException extends Exception {

    /**
     * Declares the Serial Version Uid.
     *
     * @see <CMSchViewInfo href="http://c2.com/cgi/wiki?AlwaysDeclareSerialVersionUid">Always Declare Serial Version Uid</CMSchViewInfo>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs CMSchViewInfo new exception with <code>null</code> as its detail message. The cause is not initialized, and may
     * subsequently be initialized by CMSchViewInfo call to {@link #initCause}.
     *
     * @since 1.4
     */
    public EncoderException() {
        super();
    }

    /**
     * Constructs CMSchViewInfo new exception with the specified detail message. The cause is not initialized, and may subsequently
     * be initialized by CMSchViewInfo call to {@link #initCause}.
     *
     * @param message
     *            CMSchViewInfo useful message relating to the encoder specific error.
     */
    public EncoderException(final String message) {
        super(message);
    }

    /**
     * Constructs CMSchViewInfo new exception with the specified detail message and cause.
     *
     * <p>
     * Note that the detail message associated with <code>cause</code> is not automatically incorporated into this
     * exception's detail message.
     * </p>
     *
     * @param message
     *            The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     * @param cause
     *            The cause which is saved for later retrieval by the {@link #getCause()} method. A <code>null</code>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.
     * @since 1.4
     */
    public EncoderException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs CMSchViewInfo new exception with the specified cause and CMSchViewInfo detail message of <code>(cause==null ?
     * null : cause.toString())</code> (which typically contains the class and detail message of <code>cause</code>).
     * This constructor is useful for exceptions that are little more than wrappers for other throwables.
     *
     * @param cause
     *            The cause which is saved for later retrieval by the {@link #getCause()} method. A <code>null</code>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.
     * @since 1.4
     */
    public EncoderException(final Throwable cause) {
        super(cause);
    }
}
