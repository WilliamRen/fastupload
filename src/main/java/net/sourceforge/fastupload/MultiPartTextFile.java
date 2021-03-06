/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.sourceforge.fastupload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * The class represent a boundary data is binary file. It does not convert
 * content of boundary with specific charset
 * 
 * @author <a href="mailto:link.qian@yahoo.com">Link Qian</a>
 * 
 */
public class MultiPartTextFile extends MultiPartDiskFile {

	/**
	 * charset encoding of multipart content
	 */
	private String encoding;

	private Writer writer;

	/**
	 * constructor using default charset
	 * 
	 * @param name
	 * @throws IOException
	 */
	public MultiPartTextFile(String name) throws IOException {
		super(name);
		writer = new OutputStreamWriter(new FileOutputStream(name));
	}

	/**
	 * constructor using specified charset
	 * 
	 * @param name
	 * @param charset
	 * @throws IOException
	 */
	public MultiPartTextFile(String name, String charset) throws IOException {
		super(name, charset);
		writer = new OutputStreamWriter(new FileOutputStream(name), charset);
		//writer = new OutputStreamWriter(new FileOutputStream(name));
	}

	public void append(byte[] buff, int off, int len) throws IOException {
		super.append(buff, off, len);
		if (len > 0) {
			String value = encoding.equalsIgnoreCase(charset) ? new String(buff, off, len) : new String(buff, off, len, encoding);
			writer.write(encoding.equalsIgnoreCase(charset) ? value : new String(value.getBytes(charset)));
		}
	}

	public void close() throws IOException {
		closed = true;
		writer.flush();
		writer.close();
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
