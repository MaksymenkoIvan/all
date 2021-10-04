package xyz.karpador.godfishbot;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsyncFileHelper {
	private ByteBuffer dataBuffer = null;
	private Future<Integer> currentResult = null;
	private AsynchronousFileChannel currentAfc = null;
	private final Path path;

	public AsyncFileHelper(String filePath) {
		path = Paths.get(filePath);
	}

	/**
	 * Checks if the file passed to the constructor currently exists.
	 */
	public boolean fileExists() {
		return Files.exists(path);
	}

	/**
	 * Starts reading from the file passed to the constructor.
	 */
	public void startRead() {
		try {
			currentAfc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
			int fileSize = (int) currentAfc.size();
			dataBuffer = ByteBuffer.allocate(fileSize);

			currentResult = currentAfc.read(dataBuffer, 0);
		} catch (IOException ex) {
			Logger.getLogger(AsyncFileHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Finishes the read process if needed and returns the content of the file.
	 *
	 * @return The content of the file read.
	 */
	public String getReadData() {
		if (dataBuffer == null || currentResult == null) return null;
		try {
			currentAfc.close();
			int readBytes = currentResult.get();
			Logger.getLogger(AsyncFileHelper.class.getName())
				.log(Level.INFO, String.format(
					"Bytes read: %1$d from %2$s",
					readBytes,
					path.getFileName().toString())
				);

			byte[] byteData = dataBuffer.array();
			currentResult = null;
			dataBuffer = null;
			return new String(byteData, StandardCharsets.UTF_8);
		} catch (InterruptedException | ExecutionException | IOException ex) {
			Logger.getLogger(AsyncFileHelper.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * Starts a writing process of the passed content to the file.
	 * Finishes other read/write processes before, if any.
	 *
	 * @param content The content to be written to the file.
	 */
	public void startWrite(String content) {
		if (currentResult != null)
			while (!currentResult.isDone())
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex) {
					Logger.getLogger(AsyncFileHelper.class.getName()).log(Level.SEVERE, null, ex);
				}
		try {
			currentAfc = AsynchronousFileChannel.open(path,
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);
			dataBuffer = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8));
			currentResult = currentAfc.write(dataBuffer, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}