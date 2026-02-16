package lab4.problema7.problema7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Problema7Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<HistoryEntry> history = new ArrayList<>();
		Map<String, Integer> userStats = new LinkedHashMap<>();

		Light light = new Light();
		Door door = new Door();
		Music music = new Music();
		Blinds blinds = new Blinds();

		System.out.print("Número de acciones a registrar: ");
		int n = sc.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.print("\nUsuario: ");
			String user = sc.next();
			System.out.println("Seleccione: 1. Encender luz | 2. Abrir puerta | 3. Reproducir música | 4. Ajustar volumen | 5. Ajustar persiana");
			int choice = sc.nextInt();

			Command cmd = null;
			switch (choice) {
				case 1 -> cmd = new LightOnCommand(light);
				case 2 -> cmd = new OpenDoorCommand(door);
				case 3 -> cmd = new MusicCommand(music);
				case 4 -> {
					System.out.print("Ingrese valor (0-100): ");
					cmd = new AdjustVolumeCommand(music, sc.nextInt());
				}
				case 5 -> {
					System.out.print("Ingrese valor (0-100): ");
					cmd = new AdjustBlindsCommand(blinds, sc.nextInt());
				}
			}

			if (cmd != null) {
				System.out.print("Acción " + i + " ejecutada por " + user + ": ");
				cmd.execute();

				System.out.print("¿Deshacer acción? (si/no): ");
				HistoryEntry entry = new HistoryEntry(cmd, user);
				if (sc.next().equalsIgnoreCase("si")) {
					cmd.undo();
					entry.undone = true;
				}
				history.add(entry);
				userStats.put(user, userStats.getOrDefault(user, 0) + 1);
			}
		}

		System.out.println("\n-- Historial completo ---");
		for (int i = 0; i < history.size(); i++) {
			HistoryEntry e = history.get(i);
			System.out.println((i+1) + ": " + e.command.getDescription() + " - Usuario: " + e.user + (e.undone ? " (deshecha)" : ""));
		}

		System.out.println("\n-- Investigando quién desconfiguró los electrodomésticos");
		userStats.forEach((name, count) ->
				System.out.println("Se detecta que " + name + " realizó " + count + " acción(es)."));

		sc.close();
	}
}