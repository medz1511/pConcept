package factoryMethod2;
public class Main {
    public static void main(String[] args) {
        String hashToCrack = "5d41402abc4b2a76b9719d911017c592"; // Example MD5 hash for "hello"

        LocalPasswordCracker bruteForceCracker = new BruteForceLocalPasswordCracker();
        String result = bruteForceCracker.crack(hashToCrack);
        System.out.println("Brute Force Result: " + result);

        LocalPasswordCracker dictionaryCracker = new DictionnaryLocalPasswordCracker("path/to/dictionary.txt");
        result = dictionaryCracker.crack(hashToCrack);
        System.out.println("Dictionary Result: " + result);
    }
}
