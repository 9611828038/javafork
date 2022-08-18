// Generated by jextract

package tech.deplant.java4ever.binding.ffi;

import java.lang.foreign.*;

public interface tc_response_handler_t {

    void apply(int request_id, MemorySegment params_json, int response_type, boolean finished);
    static MemorySegment allocate(tc_response_handler_t fi, MemorySession session) {
        return RuntimeHelper.upcallStub(tc_response_handler_t.class, fi, constants$0.tc_response_handler_t$FUNC, session);
    }
    static tc_response_handler_t ofAddress(MemoryAddress addr, MemorySession session) {
        MemorySegment symbol = MemorySegment.ofAddress(addr, 0, session);
        return (int _request_id, MemorySegment _params_json, int _response_type, boolean _finished) -> {
            try {
                constants$1.tc_response_handler_t$MH.invokeExact((Addressable)symbol, _request_id, _params_json, _response_type, _finished);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        };
    }
}


