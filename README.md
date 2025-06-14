
# 2048 Game in Java (Swing)

This is a **Java Swing-based** implementation of the popular 2048 puzzle game. The game uses keyboard controls to slide and combine numbered tiles to reach the number 2048.

## 🎮 Features

- Classic 2048 gameplay
- Smooth tile movement and merging logic
- Color-coded tiles
- Keeps track of current and best scores
- Win (2048) and Game Over detection
- Option to restart the game using the `R` key

## 🖥️ Screenshots

![2048 Game Screenshot](screenshot.png)

## 🔧 Requirements

- Java Development Kit (JDK) 8 or later
- Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or terminal to compile and run

## 📦 Download and Run

### Option 1: Download ZIP

1. Click the **[Download ZIP](https://github.com/AlbinMath/2048-in-java/archive/refs/heads/main.zip)** link or clone the repo:
    ```bash
    [git clone https://github.com/AlbinMath/2048-in-java.git]
    cd 2048-in-java
    ```

2. Compile:
    ```bash
    javac Game2048.java
    ```

3. Run:
    ```bash
    java Game2048
    ```

### Option 2: Run in IDE

1. Open the `.java` file in your favorite Java IDE.
2. Compile and run the `Game2048.java` file.
3. Use arrow keys to play.

## 🎮 Controls

| Key         | Action             |
|-------------|--------------------|
| Arrow Keys  | Move tiles         |
| `R`         | Restart game       |
| `Esc`       | Exit game          |

## 📝 File Structure

```
.
├── Game2048.java        # Main Java source file
├── README.md            # This file
└── screenshot.png       # Optional screenshot
```

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙌 Credits

Built with 💛 using Java and Swing.

## 📂 Using this in Your GitHub Repository

To use this project in your GitHub repository:

1. **Create a new GitHub repository** (if you haven't already).
2. **Add this project** to your GitHub repo:
    ```bash
    git init
    git remote add origin https://github.com/yourusername/2048-java-swing.git
    git add .
    git commit -m "Initial commit - 2048 Java Game"
    git push -u origin main
    ```

3. If you're using **SSH key authentication**, make sure your key is added to GitHub:
    - Generate key (if you haven't):
      ```bash
      ssh-keygen -t ed25519 -C "your_email@example.com"
      ```
    - Add it to the ssh-agent:
      ```bash
      eval "$(ssh-agent -s)"
      ssh-add ~/.ssh/id_ed25519
      ```
    - Copy the key and add it to GitHub under **Settings > SSH and GPG keys**:
      ```bash
      cat ~/.ssh/id_ed25519.pub
      ```

4. Then, push using SSH:
    ```bash
    git remote set-url origin git@github.com:yourusername/2048-java-swing.git
    git push -u origin main
    ```

---

✅ This makes it easy to collaborate, track changes, and share your 2048 game project publicly or privately using GitHub.

"# 2048-in-java" 
