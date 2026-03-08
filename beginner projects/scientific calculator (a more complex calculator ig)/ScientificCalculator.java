import java.util.Scanner;

public class ScientificCalculator {
    private static double memory = 0.0;
    private static boolean useDegrees = true;
    private static final String[] history = new String[50];
    private static int historyCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Scientific Calculator ===");
        System.out.println("Type 'help' to see available commands.\n");

        boolean running = true;
        while (running) {
            System.out.print("[" + (useDegrees ? "DEG" : "RAD") + "] > ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                continue;
            }

            switch (input) {
                case "help":
                    printHelp();
                    break;
                case "quit":
                case "exit":
                    running = false;
                    System.out.println("Calculator closed.");
                    break;
                case "mode":
                    toggleAngleMode();
                    break;
                case "history":
                    printHistory();
                    break;
                case "mc":
                    memory = 0.0;
                    System.out.println("Memory cleared.");
                    break;
                case "mr":
                    System.out.println("Memory recall: " + formatNumber(memory));
                    break;
                case "ms":
                    System.out.print("Enter value to store: ");
                    memory = readDouble(scanner);
                    System.out.println("Stored " + formatNumber(memory) + " in memory.");
                    break;
                case "m+":
                    System.out.print("Enter value to add to memory: ");
                    memory += readDouble(scanner);
                    System.out.println("Memory is now: " + formatNumber(memory));
                    break;
                case "constants":
                    printConstants();
                    break;
                case "add":
                case "+":
                    handleBinaryOp(scanner, "Addition");
                    break;
                case "sub":
                case "-":
                    handleBinaryOp(scanner, "Subtraction");
                    break;
                case "mul":
                case "*":
                    handleBinaryOp(scanner, "Multiplication");
                    break;
                case "div":
                case "/":
                    handleBinaryOp(scanner, "Division");
                    break;
                case "pow":
                    handleBinaryOp(scanner, "Power");
                    break;
                case "mod":
                case "%":
                    handleBinaryOp(scanner, "Modulus");
                    break;
                case "sqrt":
                    handleUnaryOp(scanner, "Square Root");
                    break;
                case "cbrt":
                    handleUnaryOp(scanner, "Cube Root");
                    break;
                case "abs":
                    handleUnaryOp(scanner, "Absolute Value");
                    break;
                case "sin":
                    handleUnaryOp(scanner, "Sine");
                    break;
                case "cos":
                    handleUnaryOp(scanner, "Cosine");
                    break;
                case "tan":
                    handleUnaryOp(scanner, "Tangent");
                    break;
                case "asin":
                    handleUnaryOp(scanner, "Arcsine");
                    break;
                case "acos":
                    handleUnaryOp(scanner, "Arccosine");
                    break;
                case "atan":
                    handleUnaryOp(scanner, "Arctangent");
                    break;
                case "log":
                    handleUnaryOp(scanner, "Log Base 10");
                    break;
                case "ln":
                    handleUnaryOp(scanner, "Natural Log");
                    break;
                case "exp":
                    handleUnaryOp(scanner, "e^x");
                    break;
                case "fact":
                case "!":
                    handleUnaryOp(scanner, "Factorial");
                    break;
                case "recip":
                    handleUnaryOp(scanner, "Reciprocal");
                    break;
                case "percent":
                    handlePercentage(scanner);
                    break;
                case "hyp":
                    handleHypotenuse(scanner);
                    break;
                case "nrt":
                    handleBinaryOp(scanner, "Nth Root");
                    break;
                case "quadratic":
                    handleQuadratic(scanner);
                    break;
                default:
                    System.out.println("Unknown command: '" + input + "'. Type 'help' for a list of commands.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void handleBinaryOp(Scanner scanner, String operation) {
        System.out.print("Enter first number: ");
        double a = readDouble(scanner);
        System.out.print("Enter second number: ");
        double b = readDouble(scanner);

        double result;
        String expression;

        switch (operation) {
            case "Addition":
                result = a + b;
                expression = formatNumber(a) + " + " + formatNumber(b);
                break;
            case "Subtraction":
                result = a - b;
                expression = formatNumber(a) + " - " + formatNumber(b);
                break;
            case "Multiplication":
                result = a * b;
                expression = formatNumber(a) + " * " + formatNumber(b);
                break;
            case "Division":
                if (b == 0) {
                    System.out.println("Error: Cannot divide by zero.");
                    return;
                }
                result = a / b;
                expression = formatNumber(a) + " / " + formatNumber(b);
                break;
            case "Power":
                result = Math.pow(a, b);
                expression = formatNumber(a) + " ^ " + formatNumber(b);
                break;
            case "Modulus":
                if (b == 0) {
                    System.out.println("Error: Cannot modulus by zero.");
                    return;
                }
                result = a % b;
                expression = formatNumber(a) + " mod " + formatNumber(b);
                break;
            case "Nth Root":
                if (b == 0) {
                    System.out.println("Error: Cannot take the 0th root.");
                    return;
                }
                result = Math.pow(a, 1.0 / b);
                expression = formatNumber(b) + "th root of " + formatNumber(a);
                break;
            default:
                System.out.println("Unknown binary operation.");
                return;
        }

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            System.out.println("Result: undefined (math error)");
            return;
        }

        System.out.println(expression + " = " + formatNumber(result));
        addHistory(expression + " = " + formatNumber(result));
    }

    private static void handleUnaryOp(Scanner scanner, String operation) {
        System.out.print("Enter number: ");
        double x = readDouble(scanner);

        double result;
        String expression;

        switch (operation) {
            case "Square Root":
                if (x < 0) {
                    System.out.println("Error: Cannot take square root of a negative number.");
                    return;
                }
                result = Math.sqrt(x);
                expression = "sqrt(" + formatNumber(x) + ")";
                break;
            case "Cube Root":
                result = Math.cbrt(x);
                expression = "cbrt(" + formatNumber(x) + ")";
                break;
            case "Absolute Value":
                result = Math.abs(x);
                expression = "|" + formatNumber(x) + "|";
                break;
            case "Sine":
                result = Math.sin(toCalcAngle(x));
                expression = "sin(" + formatNumber(x) + (useDegrees ? "°" : " rad") + ")";
                break;
            case "Cosine":
                result = Math.cos(toCalcAngle(x));
                expression = "cos(" + formatNumber(x) + (useDegrees ? "°" : " rad") + ")";
                break;
            case "Tangent":
                double cosCheck = Math.cos(toCalcAngle(x));
                if (Math.abs(cosCheck) < 1e-10) {
                    System.out.println("Error: Tangent is undefined at this angle.");
                    return;
                }
                result = Math.tan(toCalcAngle(x));
                expression = "tan(" + formatNumber(x) + (useDegrees ? "°" : " rad") + ")";
                break;
            case "Arcsine":
                if (x < -1 || x > 1) {
                    System.out.println("Error: Arcsine input must be between -1 and 1.");
                    return;
                }
                result = fromCalcAngle(Math.asin(x));
                expression = "asin(" + formatNumber(x) + ")";
                break;
            case "Arccosine":
                if (x < -1 || x > 1) {
                    System.out.println("Error: Arccosine input must be between -1 and 1.");
                    return;
                }
                result = fromCalcAngle(Math.acos(x));
                expression = "acos(" + formatNumber(x) + ")";
                break;
            case "Arctangent":
                result = fromCalcAngle(Math.atan(x));
                expression = "atan(" + formatNumber(x) + ")";
                break;
            case "Log Base 10":
                if (x <= 0) {
                    System.out.println("Error: Logarithm input must be positive.");
                    return;
                }
                result = Math.log10(x);
                expression = "log10(" + formatNumber(x) + ")";
                break;
            case "Natural Log":
                if (x <= 0) {
                    System.out.println("Error: Natural log input must be positive.");
                    return;
                }
                result = Math.log(x);
                expression = "ln(" + formatNumber(x) + ")";
                break;
            case "e^x":
                result = Math.exp(x);
                expression = "e^" + formatNumber(x);
                break;
            case "Factorial":
                if (x < 0 || x != Math.floor(x)) {
                    System.out.println("Error: Factorial requires a non-negative integer.");
                    return;
                }
                if (x > 170) {
                    System.out.println("Error: Factorial overflow. Max input is 170.");
                    return;
                }
                result = factorial((int) x);
                expression = formatNumber(x) + "!";
                break;
            case "Reciprocal":
                if (x == 0) {
                    System.out.println("Error: Cannot take reciprocal of zero.");
                    return;
                }
                result = 1.0 / x;
                expression = "1/" + formatNumber(x);
                break;
            default:
                System.out.println("Unknown unary operation.");
                return;
        }

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            System.out.println("Result: undefined (math error)");
            return;
        }

        String suffix = "";
        if (operation.startsWith("Arc")) {
            suffix = useDegrees ? "°" : " rad";
        }

        System.out.println(expression + " = " + formatNumber(result) + suffix);
        addHistory(expression + " = " + formatNumber(result) + suffix);
    }

    private static void handlePercentage(Scanner scanner) {
        System.out.println("What is X% of Y?");
        System.out.print("Enter X (percentage): ");
        double percent = readDouble(scanner);
        System.out.print("Enter Y (base value): ");
        double base = readDouble(scanner);

        double result = (percent / 100.0) * base;
        String entry = formatNumber(percent) + "% of " + formatNumber(base) + " = " + formatNumber(result);
        System.out.println(entry);
        addHistory(entry);
    }

    private static void handleHypotenuse(Scanner scanner) {
        System.out.println("Pythagorean theorem: c = sqrt(a^2 + b^2)");
        System.out.print("Enter side a: ");
        double a = readDouble(scanner);
        System.out.print("Enter side b: ");
        double b = readDouble(scanner);

        if (a < 0 || b < 0) {
            System.out.println("Error: Side lengths must be non-negative.");
            return;
        }

        double c = Math.sqrt(a * a + b * b);
        String entry = "hyp(" + formatNumber(a) + ", " + formatNumber(b) + ") = " + formatNumber(c);
        System.out.println(entry);
        addHistory(entry);
    }

    private static void handleQuadratic(Scanner scanner) {
        System.out.println("Quadratic formula: ax^2 + bx + c = 0");
        System.out.print("Enter a: ");
        double a = readDouble(scanner);
        if (a == 0) {
            System.out.println("Error: 'a' cannot be zero (that's a linear equation).");
            return;
        }
        System.out.print("Enter b: ");
        double b = readDouble(scanner);
        System.out.print("Enter c: ");
        double c = readDouble(scanner);

        double discriminant = b * b - 4 * a * c;
        String equation = formatNumber(a) + "x^2 + " + formatNumber(b) + "x + " + formatNumber(c) + " = 0";

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println(equation);
            System.out.println("Two real roots: x1 = " + formatNumber(x1) + ", x2 = " + formatNumber(x2));
            addHistory(equation + " -> x1=" + formatNumber(x1) + ", x2=" + formatNumber(x2));
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            System.out.println(equation);
            System.out.println("One repeated root: x = " + formatNumber(x));
            addHistory(equation + " -> x=" + formatNumber(x));
        } else {
            double realPart = -b / (2 * a);
            double imagPart = Math.sqrt(-discriminant) / (2 * a);
            System.out.println(equation);
            System.out.println("Two complex roots:");
            System.out.println("  x1 = " + formatNumber(realPart) + " + " + formatNumber(imagPart) + "i");
            System.out.println("  x2 = " + formatNumber(realPart) + " - " + formatNumber(imagPart) + "i");
            addHistory(equation + " -> complex roots");
        }
    }

    private static double toCalcAngle(double angle) {
        return useDegrees ? Math.toRadians(angle) : angle;
    }

    private static double fromCalcAngle(double radians) {
        return useDegrees ? Math.toDegrees(radians) : radians;
    }

    private static void toggleAngleMode() {
        useDegrees = !useDegrees;
        System.out.println("Angle mode set to: " + (useDegrees ? "DEGREES" : "RADIANS"));
    }

    private static double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private static void addHistory(String entry) {
        if (historyCount < history.length) {
            history[historyCount] = entry;
            historyCount++;
        } else {
            for (int i = 1; i < history.length; i++) {
                history[i - 1] = history[i];
            }
            history[history.length - 1] = entry;
        }
    }

    private static void printHistory() {
        if (historyCount == 0) {
            System.out.println("No calculations yet.");
            return;
        }
        System.out.println("--- Calculation History ---");
        for (int i = 0; i < historyCount; i++) {
            System.out.println("  " + (i + 1) + ". " + history[i]);
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            String raw = scanner.nextLine().trim().toLowerCase();

            if (raw.equals("pi")) {
                return Math.PI;
            }
            if (raw.equals("e")) {
                return Math.E;
            }
            if (raw.equals("mr") || raw.equals("mem")) {
                System.out.println("  (using memory: " + formatNumber(memory) + ")");
                return memory;
            }
            if (raw.equals("ans") && historyCount > 0) {
                String lastEntry = history[historyCount - 1];
                int eqIndex = lastEntry.lastIndexOf("= ");
                if (eqIndex != -1) {
                    try {
                        double ans = Double.parseDouble(lastEntry.substring(eqIndex + 2).trim()
                                .replace("°", "").replace(" rad", ""));
                        System.out.println("  (using last answer: " + formatNumber(ans) + ")");
                        return ans;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }

            try {
                return Double.parseDouble(raw);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again (or type 'pi', 'e', 'mr', 'ans'): ");
            }
        }
    }

    private static String formatNumber(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value) && Math.abs(value) < 1e15) {
            return String.valueOf((long) value);
        }
        String formatted = String.format("%.10g", value);
        if (formatted.contains(".")) {
            formatted = formatted.replaceAll("0+$", "");
            formatted = formatted.replaceAll("\\.$", "");
        }
        return formatted;
    }

    private static void printConstants() {
        System.out.println("--- Mathematical Constants ---");
        System.out.println("  pi = " + Math.PI);
        System.out.println("  e  = " + Math.E);
        System.out.println("You can type 'pi' or 'e' when prompted for a number.");
    }

    private static void printHelp() {
        System.out.println("==================================");
        System.out.println("       SCIENTIFIC CALCULATOR      ");
        System.out.println("==================================");
        System.out.println();
        System.out.println("BASIC ARITHMETIC");
        System.out.println("  add, +      Addition");
        System.out.println("  sub, -      Subtraction");
        System.out.println("  mul, *      Multiplication");
        System.out.println("  div, /      Division");
        System.out.println("  mod, %      Modulus");
        System.out.println("  pow         Power (x^y)");
        System.out.println();
        System.out.println("ROOTS & POWERS");
        System.out.println("  sqrt        Square root");
        System.out.println("  cbrt        Cube root");
        System.out.println("  nrt         Nth root");
        System.out.println("  exp         e^x");
        System.out.println();
        System.out.println("TRIGONOMETRY");
        System.out.println("  sin         Sine");
        System.out.println("  cos         Cosine");
        System.out.println("  tan         Tangent");
        System.out.println("  asin        Arcsine");
        System.out.println("  acos        Arccosine");
        System.out.println("  atan        Arctangent");
        System.out.println();
        System.out.println("LOGARITHMS");
        System.out.println("  log         Log base 10");
        System.out.println("  ln          Natural log (base e)");
        System.out.println();
        System.out.println("SPECIAL");
        System.out.println("  fact, !     Factorial (n!)");
        System.out.println("  abs         Absolute value");
        System.out.println("  recip       Reciprocal (1/x)");
        System.out.println("  percent     X% of Y");
        System.out.println("  hyp         Hypotenuse (Pythagorean)");
        System.out.println("  quadratic   Solve ax^2 + bx + c = 0");
        System.out.println();
        System.out.println("MEMORY");
        System.out.println("  ms          Memory store");
        System.out.println("  mr          Memory recall");
        System.out.println("  m+          Add to memory");
        System.out.println("  mc          Memory clear");
        System.out.println();
        System.out.println("SETTINGS & INFO");
        System.out.println("  mode        Toggle DEG/RAD");
        System.out.println("  constants   Show pi, e");
        System.out.println("  history     View calculation log");
        System.out.println("  quit        Exit calculator");
        System.out.println();
        System.out.println("TIPS");
        System.out.println("  Type 'pi', 'e', 'mr', or 'ans'");
        System.out.println("  when prompted for a number.");
        System.out.println("==================================");
    }
}
