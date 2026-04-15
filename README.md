# 📱 WiFi Mouse Pad

> Turn your Android phone into a wireless mouse — no USB, no Bluetooth pairing needed.

WiFi Mouse Pad is an Android application that lets you control your computer's cursor directly from your smartphone over a local Wi-Fi network. Use your phone's touchscreen as a trackpad to move the mouse, click, and scroll — all wirelessly.

---

## ✨ Features

- **Touchpad Control** — Use your phone screen as a virtual trackpad to move the cursor smoothly
- **Left & Right Click** — Tap gestures map to mouse button events on the host PC
- **Wi-Fi Based** — Works entirely over your local network; no cables or Bluetooth required
- **Lightweight** — Minimal UI focused purely on control

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| Platform | Android |
| Communication | TCP Sockets over Wi-Fi |
| Build System | Gradle (Kotlin DSL) |
| IDE | Android Studio |

---

## 📋 Requirements

**Android App (Client)**
- Android 7.0 (API level 24) or higher
- Connected to the same Wi-Fi network as the host PC

**Host PC (Server)**
- Python 3.11 with `pyautogui` installed, **or** any compatible mouse server that listens on a TCP socket
- Connected to the same Wi-Fi network as the Android device

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Blackdevil-com/WiFi-Mouse-Pad.git
cd WiFi-Mouse-Pad
```

### 2. Set Up the PC Server

On your computer, run a simple socket server that listens for mouse commands. Example using Python:

```python
import socket
import pyautogui

HOST = '0.0.0.0'
PORT = 5000  # Must match the port configured in the app

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    print(f"Server listening on port {PORT}...")
    conn, addr = s.accept()
    with conn:
        print(f"Connected by {addr}")
        while True:
            data = conn.recv(1024)
            if not data:
                break
            # Parse and execute mouse commands
```

> Install dependencies: `pip install pyautogui`

### 3. Build & Run the Android App

1. Open the project in **Android Studio**
2. Connect your Android device or start an emulator
3. Click **Run ▶** to build and install the app
4. Enter your **PC's local IP address** and **port** in the app
5. Tap **Connect** and start controlling your cursor

---

## 📁 Project Structure

```
WiFi-Mouse-Pad/
├── app/
│   └── src/
│       └── main/
│           ├── java/          # Kotlin source files (UI + socket logic)
│           ├── res/           # Layouts, drawables, strings
│           └── AndroidManifest.xml
├── gradle/                    # Gradle wrapper
├── build.gradle.kts           # Project-level build config
└── settings.gradle.kts
```

---

## 🔌 How It Works

```
[Android App]  ──── TCP Socket ────>  [PC Server]
    │                                      │
Touchpad gestures                  Translates to mouse
converted to                       movements via
(dx, dy, click) data               pyautogui / Robot API
```

1. The Android app captures touch events on the trackpad surface
2. Delta values (`dx`, `dy`) and click events are serialized and sent over a TCP socket
3. The PC server receives the data and moves the system cursor accordingly

---

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is open source. See [LICENSE](LICENSE) for details.

---

## 👤 Author

**Bharath Kumar L**
- GitHub: [@Blackdevil-com](https://github.com/Blackdevil-com)
- LinkedIn: [bharath-kumar-l-0a320b319](https://linkedin.com/in/bharath-kumar-l-0a320b319)
