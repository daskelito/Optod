import javax.swing.ImageIcon;

public class MainP1 {

	public static void main(String[] args) {
		Buffer<Message> messageBuffer = new Buffer<Message>();
		Buffer<MessageProducer> producerBuffer	= new Buffer<MessageProducer>();

		MessageManager messageManager = new MessageManager(messageBuffer);
		P1Viewer v1 = new P1Viewer(messageManager, 300, 200);
		P1Viewer v2 = new P1Viewer(messageManager, 320, 320);
		P1Viewer v3 = new P1Viewer(messageManager, 200, 400);
		Viewer.showPanelInFrame(v1, "Viewer 1", 100, 50);
		Viewer.showPanelInFrame(v2, "Viewer 2", 450, 50);
		Viewer.showPanelInFrame(v3, "Viewer 3", 800, 200);
		messageManager.start();

		Producer producer = new Producer(producerBuffer, messageBuffer);
		producer.start();

		//Edit filepath below to run!
		String filepath = "C:\\Users\\Dragon\\IdeaProjects\\Optod\\Assignment1\\";
		MessageProducerInput ipManager = new MessageProducerInput(producerBuffer);
		ipManager.addMessageProducer(getArrayProducer(10,100));
		ipManager.addMessageProducer(new ShowGubbe(3000));
		ipManager.addMessageProducer(new TextfileProducer(filepath + "files/new.txt"));
	}
	
    private static ArrayProducer getArrayProducer(int times, int delay) {
		//Edit filepath below to run!
		String filepath = "C:\\Users\\Dragon\\IdeaProjects\\Optod\\Assignment1\\";
    	Message[] messages = { new Message("UP",new ImageIcon(filepath + "images/new1.jpg")),
				new Message("Going down.",new ImageIcon(filepath + "images/new2.jpg")),
    			new Message("Going down..",new ImageIcon(filepath + "images/new3.jpg")),
    			new Message("Going down...",new ImageIcon(filepath + "images/new4.jpg")),
    			new Message("Going down....",new ImageIcon(filepath + "images/new5.jpg")),
    			new Message("Almost down",new ImageIcon(filepath + "images/new6.jpg")),
    			new Message("DOWN",new ImageIcon(filepath + "images/new7.jpg")),
    			new Message("Going up.",new ImageIcon(filepath + "images/new8.jpg")),
    			new Message("Going up..",new ImageIcon(filepath + "images/new9.jpg")),
    			new Message("Almost up",new ImageIcon(filepath + "images/new10.jpg")) };
        return new ArrayProducer(messages,times,delay);       
    }
}

class ShowGubbe implements MessageProducer {
	//Edit filepath below to run!
	String filepath = "C:\\Users\\Dragon\\IdeaProjects\\Optod\\Assignment1\\";
	private int delay;
	
	public ShowGubbe(int delay) {
		this.delay = delay;
	}

	@Override
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return 1;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Message nextMessage() {
		return new Message("Hi folks...",new ImageIcon(filepath + "images/gubbe.jpg"));
	}
	
}

