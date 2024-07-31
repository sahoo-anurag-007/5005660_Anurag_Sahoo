// Computer.java
public class Computer {
    private String cpu;
    private String ram;
    private String storage;

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    public static class ComputerBuilder {
        private String cpu;
        private String ram;
        private String storage;

        public ComputerBuilder withCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public ComputerBuilder withRam(String ram) {
            this.ram = ram;
            return this;
        }

        public ComputerBuilder withStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public void displayConfig() {
        System.out.println("CPU: " + cpu);
        System.out.println("RAM: " + ram);
        System.out.println("Storage: " + storage);
    }
}

// TestBuilderPattern.java
public class BuilderPattern {
    public static void main(String[] args) {
        Computer computer = new Computer.Computer
    }
}