package sicxesimulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Representa a memória do simulador SIC/XE.
 * A memória é implementada como um ArrayList de Word's, onde cada palavra tem 24 bits.
 */
public class Memory {

	/**
	 * Lista de palavras da memória.
	 */
	private ArrayList<Word> memory;

	/**
	 * O tamanho da memória.
	 * Pode ser modificado, sendo especificado apenas um tamanho mínimo de 1KB.
	 */
	private final int SIZE = 1000; // Tamanho da memória, não tenho certeza se vai ser fixo, logo isso aqui é temp.

	/**
	 * Construtor padrão. Inicializa todas as posições da memória com Word's padrão.
	 */
	public Memory() {
		memory = new ArrayList<>(SIZE);

		for (int i = 0; i < SIZE; i++) {
			memory.add(new Word());
		}

		// DEBUG
		System.out.println("Memória inicializada com " + memory.size() + " posições.");
	}

	/**
	 * Imprime o conteúdo de cada endereço de memória.
	 * Útil para debug.
	 */
	public void printMemory() {
		for (int i = 0; i < memory.size(); i++) {
			System.out.println("Endereço " + i + ": " + memory.get(i));
		}
	}

	/**
	 * Retorna a lista com todas as palavras da memória.
	 *
	 * @return a memória como um ArrayList de Word.
	 */
	public ArrayList<Word> getMemory() {
		return memory;
	}
	
	/**
	 * Salva o conteúdo da memória em um arquivo de texto.
	 *
	 * @param fileName O caminho do arquivo onde a memória será salva.
	 */
	public void saveMemoryToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sicxesimulator/memoria/" + fileName))) {
            for (int i = 0; i < memory.size(); i++) {
				writer.write(i + ": " + Integer.toString(memory.get(i).getValue()) + "\t");
                writer.newLine();
            }
            System.out.println("Memória salva em \"sicxesimulator/memoria/" + fileName + "\".\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a memória: " + e.getMessage());
        }
    }

    /**
     * Carrega o conteúdo de um arquivo de texto para a memória.
     *
     * @param fileName O caminho do arquivo de onde a memória será carregada.
     */
    public void loadMemoryFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("sicxesimulator/memoria/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    int address = Integer.parseInt(parts[0].trim());
                    int value = Integer.parseInt(parts[1].trim()); // Base 10
                    if (address >= 0 && address < SIZE) {
                        memory.get(address).setValue(value);
                    } else {
                        System.out.println("Erro: Endereço fora dos limites.");
                    }
                }
            }
            System.out.println("Memória carregada de \"sicxesimulator/memoria/" + fileName + "\".");
        } catch (IOException e) {
            System.out.println("Erro ao carregar a memória: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor de memória: " + e.getMessage());
        }
    }
	/**
	 * Define o valor de uma posição de memória a partir de uma Word de entrada.
	 *
	 * @param address O endereço de memória a ser sobrescrito.
	 * @param value A 'Word' a ser armazenada no endereço especificado.
	 */
	public void setMemory(int address, Word value) {
		if (address >= 0 && address < SIZE) {
				memory.set(address, value); // Validação dos limites da memória.
		} else {
			System.out.println("Erro: Endereço fora dos limites.");
		}
	}

	/**
	 * Lê o valor armazenado no endereço de memória especificado.
	 *
	 * @param address O endereço de memória a ser lido.
	 * @return o valor inteiro armazenado na palavra, ou −1 se o endereço especificado estiver fora dos limites.
	 */
	public int read(int address) {
		if (address >= 0 && address < SIZE) {
			return memory.get(address).getValue();
		} else {
			System.out.println("Erro: Endereço fora dos limites.");
			return -1;
		}
	}

	/**
	 * Escreve o valor no endereço especificado a partir de um Inteiro de entrada.
	 * O valor é armazenado na memória após a aplicação da máscara de 24 bits.
	 *
	 * @param address O endereço de memória onde o valor vai ser escrito.
	 * @param value O valor inteiro a ser escrito.
	 */
	public void write(int address, int value) {
		if (address >= 0 && address < SIZE) {
			memory.get(address).setValue(value);
		} else {
			System.out.println("Erro: Endereço fora dos limites.");
		}
	}

	/**
	 * Esse método ainda não foi implementado.
	 *
	 * @param address O endereço onde está o dado.
	 * @param byteValue O endereço
	 */
	public void setByte(int address, String byteValue) {
		// TODO
	}
}
