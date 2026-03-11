# 🚀 Guía de Instalación - Sistema de Venta de Boletos

Esta guía te ayudará a ejecutar el proyecto en **Windows**, **macOS**, **Linux (RHEL/CentOS/Fedora)** y **Linux (Debian/Ubuntu)**.

---

## 📋 Requisitos Previos

Todos los sistemas operativos necesitan:
- ☕ **Java 17 o superior** (se recomienda Java 17 o 21)
- 📦 **Maven 3.6+**

---

## 🪟 Instalación en Windows

### 1️⃣ Instalar Java

#### Opción A: OpenJDK (Recomendado)
1. Descarga OpenJDK desde: https://adoptium.net/
2. Selecciona:
   - **Version**: 17 o 21 (LTS)
   - **Operating System**: Windows
   - **Architecture**: x64
3. Ejecuta el instalador `.msi`
4. Durante la instalación, marca:
   - ✅ Set JAVA_HOME variable
   - ✅ Add to PATH
5. Verifica la instalación:
```cmd
java -version
```

#### Opción B: Con Chocolatey
```powershell
# Instalar Chocolatey si no lo tienes
# (Ejecutar PowerShell como Administrador)
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

# Instalar Java y Maven
choco install openjdk17 -y
choco install maven -y
```

### 2️⃣ Instalar Maven

#### Opción A: Manual
1. Descarga Maven desde: https://maven.apache.org/download.cgi
2. Descarga el archivo `apache-maven-3.9.x-bin.zip`
3. Extrae a `C:\Program Files\Apache\maven`
4. Agrega al PATH:
   - Panel de Control → Sistema → Configuración avanzada del sistema
   - Variables de entorno → PATH
   - Agregar: `C:\Program Files\Apache\maven\bin`
5. Verifica:
```cmd
mvn -version
```

#### Opción B: Con Chocolatey
```powershell
choco install maven -y
```

### 3️⃣ Ejecutar el Proyecto

```cmd
# Clonar o navegar al directorio del proyecto
cd C:\ruta\al\proyecto\javainge

# Compilar
mvn clean package -DskipTests

# Ejecutar
java -jar target\venta-boletos-1.0.0.jar
```

### 4️⃣ Acceder a la Aplicación

Abre tu navegador en:
- http://localhost:8080/terminal/Terminal-1
- http://localhost:8080/terminal/Terminal-2
- http://localhost:8080/terminal/Terminal-3

---

## 🍎 Instalación en macOS

### 1️⃣ Instalar Homebrew (si no lo tienes)
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### 2️⃣ Instalar Java y Maven
```bash
# Instalar OpenJDK
brew install openjdk@17

# Crear enlace simbólico
sudo ln -sfn $(brew --prefix)/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk

# Instalar Maven
brew install maven

# Verificar instalación
java -version
mvn -version
```

### 3️⃣ Ejecutar el Proyecto
```bash
# Navegar al directorio del proyecto
cd ~/ruta/al/proyecto/javainge

# Compilar
mvn clean package -DskipTests

# Ejecutar
java -jar target/venta-boletos-1.0.0.jar
```

### 4️⃣ Acceder a la Aplicación

Abre tu navegador en:
- http://localhost:8080/terminal/Terminal-1
- http://localhost:8080/terminal/Terminal-2
- http://localhost:8080/terminal/Terminal-3

---

## 🐧 Instalación en Linux (RHEL/CentOS/Fedora/Rocky/AlmaLinux)

### 1️⃣ Instalar Java y Maven

#### Fedora 36+ / RHEL 9+ / Rocky 9+ / AlmaLinux 9+
```bash
# Instalar Java 17
sudo dnf install java-17-openjdk java-17-openjdk-devel -y

# Instalar Maven
sudo dnf install maven -y

# Verificar instalación
java -version
mvn -version
```

#### CentOS 7 / RHEL 7
```bash
# Instalar Java 17
sudo yum install java-17-openjdk java-17-openjdk-devel -y

# Instalar Maven (puede requerir EPEL)
sudo yum install epel-release -y
sudo yum install maven -y

# Verificar instalación
java -version
mvn -version
```

#### Si Maven no está disponible en los repositorios
```bash
# Descargar Maven manualmente
cd /opt
sudo wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
sudo tar xzf apache-maven-3.9.6-bin.tar.gz
sudo ln -s apache-maven-3.9.6 maven

# Agregar al PATH
echo 'export PATH=/opt/maven/bin:$PATH' >> ~/.bashrc
source ~/.bashrc

# Verificar
mvn -version
```

### 2️⃣ Ejecutar el Proyecto
```bash
# Navegar al directorio del proyecto
cd ~/Downloads/javainge

# Compilar
mvn clean package -DskipTests

# Ejecutar
java -jar target/venta-boletos-1.0.0.jar
```

### 3️⃣ Acceder a la Aplicación

Abre tu navegador en:
- http://localhost:8080/terminal/Terminal-1
- http://localhost:8080/terminal/Terminal-2
- http://localhost:8080/terminal/Terminal-3
- http://localhost:8080/terminal/Terminal-n
### 4️⃣ Configurar Firewall (Opcional)

Si tienes problemas de acceso:
```bash
# Fedora/RHEL 8+
sudo firewall-cmd --add-port=8080/tcp --permanent
sudo firewall-cmd --reload

# CentOS 7/RHEL 7
sudo firewall-cmd --zone=public --add-port=8080/tcp --permanent
sudo firewall-cmd --reload
```

---

## 🐧 Instalación en Linux (Debian/Ubuntu/Mint/Pop!_OS)

### 1️⃣ Instalar Java y Maven

#### Ubuntu 20.04+ / Debian 11+ / Linux Mint 20+
```bash
# Actualizar repositorios
sudo apt update

# Instalar Java 17
sudo apt install openjdk-17-jdk openjdk-17-jre -y

# Instalar Maven
sudo apt install maven -y

# Verificar instalación
java -version
mvn -version
```

#### Ubuntu 18.04 / Debian 10 (Java 17 no disponible por defecto)
```bash
# Agregar repositorio de Adoptium
sudo apt update
sudo apt install wget apt-transport-https -y
wget -O - https://packages.adoptium.net/artifactory/api/gpg/key/public | sudo apt-key add -
echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | sudo tee /etc/apt/sources.list.d/adoptium.list

# Instalar Java 17
sudo apt update
sudo apt install temurin-17-jdk -y

# Instalar Maven
sudo apt install maven -y

# Verificar
java -version
mvn -version
```

### 2️⃣ Ejecutar el Proyecto
```bash
# Navegar al directorio del proyecto
cd ~/Downloads/javainge

# Compilar
mvn clean package -DskipTests

# Ejecutar
java -jar target/venta-boletos-1.0.0.jar
```

### 3️⃣ Acceder a la Aplicación

Abre tu navegador en:
- http://localhost:8080/terminal/Terminal-1
- http://localhost:8080/terminal/Terminal-2
- http://localhost:8080/terminal/Terminal-3

### 4️⃣ Configurar Firewall (Opcional)

Si tienes `ufw` activo:
```bash
sudo ufw allow 8080/tcp
sudo ufw reload
```

---

## 🎯 Ejecución Rápida (Todos los Sistemas)

Una vez que Java y Maven estén instalados:

```bash
# 1. Navegar al directorio del proyecto
cd /ruta/al/proyecto/javainge

# 2. Compilar el proyecto
mvn clean package -DskipTests

# 3. Ejecutar la aplicación
java -jar target/venta-boletos-1.0.0.jar
```

O usar Maven directamente:
```bash
mvn spring-boot:run
```

---

## 🌐 Probar el Sistema

### Opción 1: Un solo terminal
1. Abre tu navegador
2. Ve a: http://localhost:8080/terminal/Terminal-1
3. Haz clic en asientos para comprarlos

### Opción 2: Múltiples terminales (Probar concurrencia)
1. Abre **3 pestañas** en tu navegador
2. En cada pestaña abre un terminal diferente:
   - Pestaña 1: http://localhost:8080/terminal/Terminal-1
   - Pestaña 2: http://localhost:8080/terminal/Terminal-2
   - Pestaña 3: http://localhost:8080/terminal/Terminal-3
3. Intenta comprar el mismo asiento desde diferentes terminales
4. Observa cómo solo una compra tiene éxito (sincronización funcionando)

---

## 🛑 Detener la Aplicación

### Windows
```cmd
# En la ventana CMD donde se ejecutó, presiona:
Ctrl + C
```

### macOS / Linux
```bash
# En la terminal donde se ejecutó, presiona:
Ctrl + C

# O encontrar y matar el proceso:
ps aux | grep venta-boletos
kill <PID>

# O usar pkill:
pkill -f venta-boletos
```

---

## 🔧 Solución de Problemas

### Puerto 8080 ya en uso

**Windows:**
```cmd
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

**macOS / Linux:**
```bash
lsof -i :8080
kill -9 <PID>
```

O cambiar el puerto en `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Error: JAVA_HOME no está configurado

**Windows:**
```cmd
# Establecer JAVA_HOME temporalmente
set JAVA_HOME=C:\Program Files\Java\jdk-17

# O permanentemente (Panel de Control → Variables de entorno)
```

**macOS:**
```bash
# Agregar a ~/.zshrc o ~/.bash_profile
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH
source ~/.zshrc  # o source ~/.bash_profile
```

**Linux:**
```bash
# Agregar a ~/.bashrc
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
source ~/.bashrc
```

### Maven no compila

```bash
# Limpiar caché de Maven
mvn clean install -U

# O eliminar caché completo
rm -rf ~/.m2/repository  # macOS/Linux
rmdir /s %USERPROFILE%\.m2\repository  # Windows
```

### Página en blanco o error 404

Verifica que estés accediendo a la ruta correcta:
- ✅ CORRECTO: http://localhost:8080/terminal/Terminal-1
- ❌ INCORRECTO: http://localhost:8080

---

## 📊 Verificar que todo funciona

1. **La aplicación inicia sin errores**
   ```
   ==============================================
   Aplicación iniciada correctamente
   Accede a: http://localhost:8080/terminal/Terminal-1
   ==============================================
   ```

2. **El puerto está escuchando**
   
   **Windows:**
   ```cmd
   netstat -an | findstr :8080
   ```
   
   **macOS/Linux:**
   ```bash
   lsof -i :8080
   # o
   ss -tlnp | grep 8080
   ```

3. **La interfaz web carga correctamente**
   - Ves la grilla de 40 asientos
   - Los asientos disponibles están en verde
   - Puedes hacer clic en ellos para comprar

4. **La sincronización funciona**
   - Abre múltiples terminales
   - El mismo asiento no se puede comprar dos veces
   - Cada terminal muestra su propio ID al comprar

---

## 🚀 Comandos de Referencia Rápida

| Acción | Windows | macOS/Linux |
|--------|---------|-------------|
| Ver Java version | `java -version` | `java -version` |
| Ver Maven version | `mvn -version` | `mvn -version` |
| Compilar proyecto | `mvn clean package -DskipTests` | `mvn clean package -DskipTests` |
| Ejecutar JAR | `java -jar target\venta-boletos-1.0.0.jar` | `java -jar target/venta-boletos-1.0.0.jar` |
| Ejecutar con Maven | `mvn spring-boot:run` | `mvn spring-boot:run` |
| Detener aplicación | `Ctrl + C` | `Ctrl + C` |
| Ver puerto 8080 | `netstat -an \| findstr :8080` | `lsof -i :8080` |

---

## 📚 Documentación Adicional

- [README.md](README.md) - Información general del proyecto
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)

---

**¿Necesitas ayuda?** Revisa la sección de solución de problemas o verifica los logs en la consola donde ejecutas la aplicación.
