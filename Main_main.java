import java.util.PriorityQueue;

public class Main_main {
	public static void main(String[] args) {
		ERQueue erQueue = new ERQueue();

		erQueue.arrive("Pedro Cruz", 1, "Head injury - NOW UNCONSCIOUS ⚠", "23:52");
		erQueue.arrive("Carlos Mendoza", 2, "Compound fracture - leg", "23:50");
		erQueue.arrive("Lisa Wang", 2, "Severe asthma attack", "23:56");
		erQueue.arrive("Maria Santos", 3, "Deep laceration on arm", "23:48");
		erQueue.arrive("Ana Lim", 4, "Sprained ankle", "23:49");

		erQueue.displayQueue();
		erQueue.treatNext();
		erQueue.displayQueue();
	}
}

class Patient implements Comparable<Patient> {
	private String name;
	private int priority;
	private String condition;
	private String arrivalTime;

	public Patient(String name, int priority, String condition, String arrivalTime) {
		this.name = name;
		this.priority = priority;
		this.condition = condition;
		this.arrivalTime = arrivalTime;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}

	public String getCondition() {
		return condition;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public int compareTo(Patient otherPatient) {
		if (this.priority != otherPatient.priority) {
			return Integer.compare(this.priority, otherPatient.priority);
		}
		return this.arrivalTime.compareTo(otherPatient.arrivalTime);
	}

	@Override
	public String toString() {
		return String.format("[P%d] %s - %s (%s)", priority, name, condition, arrivalTime);
	}
}

class ERQueue {
	private PriorityQueue<Patient> queue;

	public ERQueue() {
		queue = new PriorityQueue<>();
	}

	public void arrive(String name, int priority, String condition, String time) {
		Patient newPatient = new Patient(name, priority, condition, time);
		queue.offer(newPatient);
		System.out.println("Patient " + name + " has arrived and is added to the queue.");
	}

	public void treatNext() {
		if (queue.isEmpty()) {
			System.out.println("No patients to treat.");
			return;
		}
		Patient treatedPatient = queue.poll();
		System.out.println("\n>>> Treating patient now...");
		System.out.println("Treated: " + treatedPatient);
	}

	public void displayQueue() {
		if (queue.isEmpty()) {
			System.out.println("No patients in the queue.");
			return;
		}

		System.out.println("\n=== UPDATED QUEUE ===");
		System.out.println("Patients Waiting: " + queue.size());
		int i = 1;
		for (Patient patient : queue) {
			System.out.println(i + ". " + patient);
			i++;
		}
	}
}