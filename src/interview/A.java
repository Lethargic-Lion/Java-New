package interview;

class A {
    class B {
        class C {
            class D {
                public int addition(int a , int b) {
                    return a+b;
                }
            }
        }
    }

    public static void main(String [] args) {
//        A a = new A();
//        A.B b = a.new B();
//        A.B.C c = b.new C();
//        A.B.C.D d = c.new D();

        // Function to find smallest word in a sentence after removing duplicates characters
        String s = "Explore the impact off two line powerful quotes. Get inspired by short, memorable lines that pack aaa punch and discover why they resonate";
        String[] words = s.split(" ");
        String longest = s;

        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char ch : word.toCharArray()) {
                if (sb.indexOf(String.valueOf(ch)) == -1) {
                    sb.append(ch);
                }
            }
            String uniqueCharWord = sb.toString();
            if (uniqueCharWord.length() < longest.length()) {
                longest = uniqueCharWord;
            }
        }

        System.out.println("Longest word after removing duplicates: " + longest);
        int[] hash = new int[256];
        System.out.println(hash[0]);
    }

}
