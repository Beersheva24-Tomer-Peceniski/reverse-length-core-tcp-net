package telran.appl.net;

import telran.net.*;

public class ReverseLengthProtocol implements Protocol {

    @Override
    public Response getResponse(Request request) {
        String requestType = request.requestType();
        String requestData = request.requestData();
        String responseData = switch (requestType) {
            case "reverse" -> new StringBuilder(requestData).reverse().toString();
            case "length" -> requestData.length() + "";
            default -> "Wrong type";
        };
        ResponseCode responseCode = switch(responseData) {
            case "Wrong type" -> ResponseCode.WRONG_TYPE;
            default -> ResponseCode.OK;
        };
        return new Response(responseCode, responseData);
    }

}
