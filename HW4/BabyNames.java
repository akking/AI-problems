package HW4;

import java.util.*;

/**
 * Created by DLI on 2/23/17.
 */
public class BabyNames {

    private static class TransitionEntry {
        int total = 0; // save the total to avoid re-compute
        /**
         * Use a `TreeMap`, when select which Character to used next
         * random.nextInt(total) + 1 => randomly get number: [1, total]
         * then TreeMap.ceilingEntry(random.nextInt(total) + 1).getValue()
         *
         * eg: for prefix "ab":
         * if 'c' showed 3 times, 'd' showed 4 times, 'e' showed 2 times. total is 3 + 4 + 2 = 9
         * in the tree map, I put: 3 => 'c', 7 => 'd', 9 => 'e'
         * also, could be        : 4 => 'd', 6 => 'e', 9 => 'c'
         * the order doesn't matter
         * then call random.nextInt(9) + 1 which gives me a random number 'randN'
         * from 1 to 9, inclusive,
         * if 3 >= randN >= 1, the least key in the TreeMap that >= randN is 3, then select 'c'
         * if 7 >= randN >= 4, then select 'd'
         * if 9 >= randN >= 8, then select 'e'
         *
         */
        TreeMap<Integer, Character> map = new TreeMap<>();
    }

    final int order;
    Iterator<String> babyNameItr;
    private Map<String, TransitionEntry> transitionMap = new HashMap<>();
    Random random = new Random();
    Set<String> realNames = new HashSet<>();

    public BabyNames(int order, int babySex) {
        this.order = order;
        babyNameItr = new NameIterator(babySex);
    }

    public void learn() {
        // key: for each prefix, value: a map
        // key of the map is the character next to prefix, value is the times it shows
        Map<String, Map<Character, Integer>> map = new HashMap<>();
        while (babyNameItr.hasNext()) {
            String originalName;
            String name = processName((originalName = babyNameItr.next()), this.order);
            realNames.add(originalName);
            for (int i = 0; i < name.length() - this.order; i++) {
                // get the prefix, len(prefix) == order
                String prefix = name.substring(i, i + this.order);
                // get the char next to prefix
                char c = name.charAt(i + this.order);
                Map<Character, Integer> frequency;
                map.putIfAbsent(prefix, new HashMap<>());
                frequency = map.get(prefix);
                frequency.putIfAbsent(c, 0);
                frequency.put(c, frequency.get(c) + 1);
            }
        }
        for (Map.Entry<String, Map<Character, Integer>> entry : map.entrySet()) {
            int count = 0;
            String prefix = entry.getKey();
            TransitionEntry entryForPrefix = new TransitionEntry();
            transitionMap.put(prefix, entryForPrefix);
            for (Map.Entry<Character, Integer> e : entry.getValue().entrySet()) {
                count += e.getValue();
                entryForPrefix.map.put(count, e.getKey());
            }
            entryForPrefix.total = count;
        }
    }

    public char giveNextName(String prefix) {
        TransitionEntry te = transitionMap.get(prefix);
        int total = te.total;
        int num = random.nextInt(total) + 1;
        return te.map.ceilingEntry(num).getValue();
    }

    private static String processName(String name, int order) {
        // add `order` times of "_" to the start and end of name
        StringBuilder sb = new StringBuilder(name);
        for (int i = 0; i < order; i++) {
            sb.insert(0, "_");
            sb.append("_");
        }
        return sb.toString();
    }

    public boolean isValidNewName(String name, int min, int max) {
        return !realNames.contains(name) && name.length() >= min && name.length() <= max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String confirm;
        int order;
        String babySex;
        int numNames, minLen, maxLen;
        do {
            System.out.println("You want a boy or girl name? (enter \"b\" for boy and \"g\" for girl");
            babySex = sc.nextLine();

            System.out.println("How many names you want?");
            numNames = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter the order degree");
            order = sc.nextInt();
            sc.nextLine();


            System.out.println("Enter the minimum length of the name: ");
            minLen = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter the max length of the name: ");
            maxLen = sc.nextInt();
            sc.nextLine();

            System.out.println("You want a " + (babySex.equals("b") ? "boy's " : "girl's " + "name, ")
                    + "with order of " + order + ", length from " + minLen + " to " + maxLen
                    + "\nAre you sure?(enter yes for sure)");
            confirm = sc.nextLine();
        } while (!confirm.equals("yes"));

        Set<String> rst = new HashSet<>();
        BabyNames namesGenerator = new BabyNames(order, babySex.equals("b") ? 1 : 0);
        namesGenerator.learn();

        while (rst.size() < numNames) {
            StringBuilder newName = new StringBuilder();
            for (int i = 0; i < namesGenerator.order; i++) {
                newName.append("_");
            }
            StringBuilder pre = new StringBuilder(newName.toString());
            char next;
            while ((next = namesGenerator.giveNextName(pre.toString())) != '_') {
                newName.append(next);
                pre.deleteCharAt(0).append(next);
            }
            String newNameStr = newName.substring(namesGenerator.order);
            if (namesGenerator.isValidNewName(newNameStr, minLen, maxLen)) {
                if (rst.add(newNameStr)) {
                    System.out.println(newNameStr);
                }
            }
        }
    }
}
