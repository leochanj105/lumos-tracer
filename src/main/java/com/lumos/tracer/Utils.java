package com.lumos.tracer;

public class Utils{

        public static byte[] long2barr(long value) {
                // return new byte[8];
                // return ByteBuffer.allocateDirect(8).putLong(value);
                return new byte[] {
                                (byte) (value >>> 56),
                                (byte) (value >>> 48),
                                (byte) (value >>> 40),
                                (byte) (value >>> 32),
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte) value
                };
        }

        public static byte[] int2barr(int value) {
                // return new byte[8];
                // return ByteBuffer.allocateDirect(4).putInt(value);
                return new byte[] {
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte)  value
                };
        }

        public static byte[] getPayload(String s){
                // return new byte[s.length() + 4];
                //byte[] sb = s.getBytes();
                // ByteBuffer buf = ByteBuffer.allocateDirect(sb.length + 4).putInt(sb.length).put(sb);
                // buf.putInt(s.length());
                // buf.put(s.getBytes());
                int length = s.length();
                byte[] lpart = int2barr(length);
                byte[] content = s.getBytes();
                byte[] payload = new byte[lpart.length + content.length];
                System.arraycopy(lpart, 0, payload, 0, lpart.length);
                System.arraycopy(content, 0, payload, lpart.length, content.length);
                return payload;
        }

        public static byte[] getPayload(int id, String content){
                // return new byte[content.length() + 4];
                // byte[] sb = content.getBytes();
                // ByteBuffer buf = ByteBuffer.allocateDirect(sb.length + 12).putLong(id).putInt(sb.length).put(sb);
                byte[] idpart = int2barr(id);
                int length = content.length();
                byte[] lpart = int2barr(length);
                byte[] cpart = content.getBytes();
                byte[] payload = new byte[idpart.length + lpart.length + cpart.length];
                System.arraycopy(idpart, 0, payload, 0, idpart.length);
                System.arraycopy(lpart, 0, payload, idpart.length, lpart.length);
                System.arraycopy(cpart, 0, payload, idpart.length + lpart.length, cpart.length);
                return payload;
        }

        public static byte[] getPayload(int id, long value){
                // return ByteBuffer.allocateDirect(16).putLong(id).putLong(value);
                return new byte[] {
                                // (byte) (id >>> 56),
                                // (byte) (id >>> 48),
                                // (byte) (id >>> 40),
                                // (byte) (id >>> 32),
                                (byte) (id >>> 24),
                                (byte) (id >>> 16),
                                (byte) (id >>> 8),
                                (byte) id,
                                // (byte) 8,
                                (byte) (value >>> 56),
                                (byte) (value >>> 48),
                                (byte) (value >>> 40),
                                (byte) (value >>> 32),
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte) value
                };
        }

        public static byte[] getPayload(int id, int value){
                // return ByteBuffer.allocateDirect(12).putLong(id).putInt(value);
                // return new byte[8];
                return new byte[] {
                                // (byte) (id >>> 56),
                                // (byte) (id >>> 48),
                                // (byte) (id >>> 40),
                                // (byte) (id >>> 32),
                                (byte) (id >>> 24),
                                (byte) (id >>> 16),
                                (byte) (id >>> 8),
                                (byte) id,
                                // (byte) 4,
                                (byte) (value >>> 24),
                                (byte) (value >>> 16),
                                (byte) (value >>> 8),
                                (byte) value
                };
        }

        public static byte[] getPayload(int id, byte[] value){
                // return ByteBuffer.allocateDirect(value.length + 12).putLong(id).putInt(value.length).put(value);
                // return new byte[4+value.length];
                // byte[] idpart = new byte[] {
                                // (byte) (id >>> 56),
                                // (byte) (id >>> 48),
                                // (byte) (id >>> 40),
                                // (byte) (id >>> 32),
                                // (byte) (id >>> 24),
                                // (byte) (id >>> 16),
                                // (byte) (id >>> 8),
                                // (byte) id
                // };
                int length = value.length;
                byte[] lpart = int2barr(length);
                byte[] res = new byte[4 + lpart.length + value.length];
                // res[0] = (byte) (id >>> 56);
                // res[1] = (byte) (id >>> 48);
                // res[2] = (byte) (id >>> 40);
                // res[3] = (byte) (id >>> 32);
                res[0] = (byte) (id >>> 24);
                res[1] = (byte) (id >>> 16);
                res[2] = (byte) (id >>> 8);
                res[3] = (byte) id;
                System.arraycopy(lpart, 0, res, 4, lpart.length);
                System.arraycopy(value, 0, res, 4 + lpart.length, value.length);
                return value;
        }
}
