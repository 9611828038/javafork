package tech.deplant.java4ever.binding.ffi;

import lombok.extern.log4j.Log4j2;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;

@Log4j2
public class EverSdkResponse implements tc_response_handler_t {

    private String result;

    /**
     * @param x0 uint32_t request_id
     * @param x1 tc_string_data_t params_json
     * @param x2 uint32_t response_type
     * @param x3 bool finished
     */
    @Override
    public void apply(int x0, MemorySegment x1, int x2, boolean x3) {
        try (MemorySession scope = MemorySession.openShared()) {
            if (x2 == 0) {
                this.result = EverSdkBridge.toString(x1);
                //log.trace("REQID:" + x0 + " returned RESULT");
            } else if (x2 == 1) {
                throw new RuntimeException(EverSdkBridge.toString(x1));
                //this.result.complete(tc_string_data_t.toString(x1, scope));
                //log.warn("REQID:" + x0 + " returned ERROR");
            } else if (x2 == 2) {
                // NOP = 2, no operation. In combination with finished = true signals that the request handling was finished.
                //log.trace("REQID:" + x0 + " returned NOP");
            } else if (x2 == 3) {
                // APP_REQUEST = 3, request some data from application. See Application objects
                //log.trace("REQID:" + x0 + " returned APP_REQUEST");
            } else if (x2 == 4) {
                // APP_NOTIFY = 4, notify application with some data. See Application objects
                //log.trace("REQID:" + x0 + " returned APP_NOTIFY");
            } else if (x2 >= 5 && x2 <= 99) {
                // RESERVED = 5..99 – reserved for protocol internal purposes. Application (or binding) must ignore this response.
                // Nevertheless the binding must check the finished flag to release data, associated with request.
                //log.trace("REQID:" + x0 + " returned RESERVED");
            } else {
                // CUSTOM >= 100 - additional function data related to request handling. Depends on the function.
                //log.trace("REQID:" + x0 + " returned CUSTOM");
            }
        }
    }

    public String result() {
        return this.result;
    }
}
