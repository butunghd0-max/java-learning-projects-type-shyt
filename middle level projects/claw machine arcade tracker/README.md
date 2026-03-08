# Claw Machine Arcade Tracker (Java)

Simple OOP simulation of an arcade claw machine with payout-aware grip logic.

## Run

```powershell
javac src\*.java
java -cp src Main
```

## Classes

- `Prize`: immutable prize data (`name`, `weight`, `value`)
- `Claw`: win/loss probability using grip strength and random roll
- `GameSession`: tracks plays, wins, money, payout rate, and session stats
- `Main`: builds a sample prize pool and runs a simulation
