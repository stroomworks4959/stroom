/*
 * Copyright 2018 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package stroom.lmdb.serde;

import java.nio.ByteBuffer;

public class IntegerSerde implements Serde<Integer> {

    @Override
    public Integer deserialize(final ByteBuffer byteBuffer) {
        final Integer val = byteBuffer.getInt();
        byteBuffer.flip();
        return val;
    }

    @Override
    public void serialize(final ByteBuffer byteBuffer, final Integer val) {
        byteBuffer.putInt(val);
        byteBuffer.flip();
    }

    public void increment(final ByteBuffer byteBuffer) {
        final int val = byteBuffer.getInt();
        byteBuffer.flip();
        byteBuffer.putInt(val + 1);
        byteBuffer.flip();
    }

    public void decrement(final ByteBuffer byteBuffer) {
        final int val = byteBuffer.getInt();
        byteBuffer.flip();
        byteBuffer.putInt(val - 1);
        byteBuffer.flip();
    }

    @Override
    public int getBufferCapacity() {
        return Integer.BYTES;
    }
}
