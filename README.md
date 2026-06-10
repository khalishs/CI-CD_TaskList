<h1 align="center">📋 Task Manager Application</h1>

<p align="center">
  Aplikasi manajemen tugas berbasis <b>Java (Maven)</b> untuk mengelola tugas
  <b>Personal</b> dan <b>Work</b>, lengkap dengan autentikasi pengguna dan
  penyimpanan data persisten. Repository ini disiapkan untuk penerapan
  pipeline <b>CI/CD</b> dengan tahap <i>integration</i>, <i>testing</i>,
  <i>inspection</i>, dan <i>deployment</i>.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Test-JUnit5-25A162?logo=junit5&logoColor=white" alt="JUnit 5">
  <img src="https://img.shields.io/badge/License-MIT-blue" alt="MIT License">
</p>

---

## 📑 Daftar Isi

- [Deskripsi Singkat Proyek](#-deskripsi-singkat-proyek)
- [Arsitektur Pipeline CI/CD](#-arsitektur-pipeline-cicd)
- [Pembagian Tugas Anggota Kelompok](#-pembagian-tugas-anggota-kelompok)
- [Tools & Teknologi per Tahap Pipeline](#-tools--teknologi-per-tahap-pipeline)
- [Fitur Aplikasi](#-fitur-aplikasi)
- [Struktur Proyek](#-struktur-proyek)
- [Panduan Menjalankan Secara Lokal](#-panduan-menjalankan-secara-lokal)
- [Contoh Penggunaan](#-contoh-penggunaan)
- [Error Handling](#-error-handling)
- [Lisensi](#-lisensi)

---

## 📝 Deskripsi Singkat Proyek

**Task Manager** adalah aplikasi konsol (CLI) yang memungkinkan pengguna untuk
melakukan registrasi/login, lalu menambah, mengubah, menghapus, dan menampilkan
daftar tugas. Setiap tugas dikategorikan sebagai **Personal** atau **Work**, dan
seluruh data disimpan secara persisten ke dalam berkas `.dat` menggunakan
serialisasi objek Java.

Proyek ini dibangun dengan **Java 21** dan **Apache Maven**, serta dirancang agar
mudah diintegrasikan ke dalam pipeline **CI/CD** dengan empat tahap utama:
**integration**, **testing**, **inspection**, dan **deployment**.

---

## 🏗️ Arsitektur Pipeline CI/CD

Pipeline mengikuti empat tahap berurutan dari kode hingga rilis:

```
   ┌───────────────┐   ┌───────────────┐   ┌───────────────┐   ┌───────────────┐
   │  INTEGRATION  │ → │    TESTING    │ → │  INSPECTION   │ → │  DEPLOYMENT   │
   │  build &      │   │  unit test    │   │  analisis     │   │  package &    │
   │  compile      │   │  (JUnit 5)    │   │  kualitas     │   │  rilis JAR    │
   └───────────────┘   └───────────────┘   └───────────────┘   └───────────────┘
```

| Tahap | Aktivitas | Output |
|-------|-----------|--------|
| **1. Integration** | Setiap `push`/`pull request` memicu pipeline. Kode dari semua kontributor digabung lalu dikompilasi (`mvn compile`). | Kode terkompilasi |
| **2. Testing** | Unit test JUnit 5 dijalankan otomatis (`mvn test`) untuk memverifikasi fungsionalitas. | Laporan hasil uji |
| **3. Inspection** | Analisis kualitas kode statis (mis. coverage, *code smells*, gaya penulisan) sebagai *quality gate*. | Laporan kualitas |
| **4. Deployment** | Artefak JAR executable dibuat (`mvn package`) lalu dipublikasikan / di-deploy ke environment target. | `target/tasklist.jar` |

> ℹ️ Definisi workflow pipeline (mis. GitHub Actions) **belum disertakan** dan
> akan diimplementasikan secara terpisah.

---

## 👥 Pembagian Tugas Anggota Kelompok

| Nama Anggota | NIM | Komponen / Tanggung Jawab |
|--------------|-----|---------------------------|
| Rakha Raihanurrahman | 103022300046 | **Integration** — manajemen repository, branching, & proses build/kompilasi |
| Muhammad Fadli Achsan K | 103022300018 | **Testing** — unit testing (JUnit 5) & pelaporan hasil uji |
| Khalish Tianto Wiriadinata | 103022300136 | **Inspection** — analisis kualitas kode & *quality gate* |
| Anak Agung Aryadipa Aditya Nugraha | 103022300063 | **Deployment** — pembuatan artefak JAR, rilis, & deploy ke environment target |

---

## 🛠️ Tools & Teknologi per Tahap Pipeline

| Tahap Pipeline | Tools / Teknologi |
|----------------|-------------------|
| **Integration** | Git, GitHub, Apache Maven, `maven-compiler-plugin` |
| **Testing** | JUnit 5 (Jupiter), `maven-surefire-plugin` |
| **Inspection** | _(isi: mis. JaCoCo / SonarQube / SpotBugs / Checkstyle)_ |
| **Deployment** | `maven-jar-plugin` (executable JAR), _(isi: target deploy mis. GitHub Releases / server)_ |
| **CI/CD Orchestration** | _(isi: mis. GitHub Actions / GitLab CI / Jenkins)_ |

> Bahasa & runtime inti: **Java 21**.

---

## 🌟 Fitur Aplikasi

- **🔐 User Authentication** — registrasi & login pengguna agar tugas tetap privat.
- **🗂️ Task Management**
  - Menambah tugas baru (nama, deskripsi, tenggat waktu).
  - Mengubah tugas yang sudah ada.
  - Menghapus tugas.
  - Menampilkan semua tugas beserta sisa waktu hingga tenggat.
- **🏷️ Task Categories** — tugas dikategorikan sebagai **Personal** atau **Work**.
- **💾 Persistence** — tugas disimpan ke berkas `.dat` agar tidak hilang saat aplikasi ditutup.
- **⚙️ Account Management** — ubah password & hapus akun.

---

## 📂 Struktur Proyek

```
TaskList-Maven/
├── pom.xml                     # Konfigurasi Maven (build, test, package)
├── README.md
├── LICENSE
├── .gitignore
└── src/
    ├── main/java/
    │   ├── tasklist/           # Entry point & tampilan menu (CLI)
    │   │   ├── Tasklist.java    #  └─ main class
    │   │   └── Menu.java
    │   ├── task/               # Logika & model tugas
    │   │   ├── Task.java
    │   │   ├── PersonalTask.java
    │   │   ├── WorkTask.java
    │   │   ├── TaskInter.java
    │   │   └── TaskManager.java
    │   └── user/               # Autentikasi & manajemen akun
    │       ├── Account.java
    │       ├── Auth.java
    │       └── UserManager.java
    └── test/java/              # Unit test (JUnit 5)
        ├── task/
        └── user/
```

---

## 🚀 Panduan Menjalankan Secara Lokal

### Prasyarat

| Tool | Versi | Unduhan |
|------|-------|---------|
| **Java JDK** | 21 atau lebih tinggi | [Unduh JDK](https://www.oracle.com/java/technologies/downloads/) |
| **Apache Maven** | 3.8+ | [Unduh Maven](https://maven.apache.org/download.cgi) |

Pastikan keduanya sudah terpasang:

```bash
java -version
mvn -version
```

### Langkah-langkah

**1. Clone repository**

```bash
git clone <url-repository-anda>
cd TaskList-Maven
```

**2. Jalankan unit test**

```bash
mvn clean test
```

**3. Build menjadi JAR executable**

```bash
mvn clean package
```

**4. Jalankan aplikasi**

```bash
java -jar target/tasklist.jar
```

> 💡 Aplikasi ini berbasis CLI dan membutuhkan input keyboard, jadi jalankan
> melalui terminal yang interaktif.

---

## 📚 Contoh Penggunaan

**Tambah Tugas**

```text
Nama Tugas: Belajar Java
Deskripsi: Belajar tentang Polymorphism
Tenggat Waktu (dd-MM-yyyy): 12-12-2025
Jenis Tugas (Personal/Work): Personal
```

**Tampilkan Tugas**

```text
Tugas 1
Nama Tugas      : Belajar Java
Deskripsi       : Belajar tentang Polymorphism
Tenggat Waktu   : 12-12-2025
Jenis Tugas     : Personal
Sisa Waktu      : 30 hari lagi.
```

---

## ⚠️ Error Handling

| Kasus | Penanganan |
|-------|------------|
| **Format tanggal tidak valid** | Input selain `dd-MM-yyyy` ditolak dengan pesan error. |
| **Tenggat sudah lewat** | Tugas dengan tenggat di masa lampau tidak diterima. |
| **Jenis tugas tidak valid** | Hanya `Personal` atau `Work` yang diterima. |
| **Input kosong** | Field wajib tidak boleh dikosongkan. |

---

## 📄 Lisensi

Proyek ini dilisensikan di bawah **MIT License**. Lihat berkas
[LICENSE](LICENSE) untuk detail selengkapnya.
