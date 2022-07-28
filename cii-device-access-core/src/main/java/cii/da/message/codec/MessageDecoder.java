package cii.da.message.codec;


import cii.da.message.codec.model.Point;

import java.util.List;

public interface MessageDecoder extends Protocol {
   List<Point> decode(MessageContext context );

   String getProtocolCode();
}
