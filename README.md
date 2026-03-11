# Sistema de Venta de Boletos

Sistema de venta de asientos desarrollado con **Spring Boot** que permite a múltiples terminales comprar asientos de forma concurrente.

## � Documentación

- **[INSTALL.md](INSTALL.md)** - 🚀 Guía completa de instalación para Windows, macOS, Linux (RHEL/Debian)- **[NETWORK.md](NETWORK.md)** - 🌐 Configuración para acceso desde la red local (LAN)
- **[DINAMICAS.md](DINAMICAS.md)** - 🎉 Dinámicas, premios y gamificación para ferias- **[TESTING.md](TESTING.md)** - 🧪 Guía de pruebas y verificación del sistema- **[CHANGES.md](CHANGES.md)** - 📝 Registro detallado de todos los cambios realizados- **[README.md](README.md)** - 📖 Este archivo (información general)

## �📋 Características

- 40 asientos disponibles
- Múltiples terminales pueden operar simultáneamente
- Sincronización para evitar conflictos en ventas concurrentes
- Interfaz web responsiva con **Bootstrap 5**
- Actualización automática cada 5 segundos

## � Dinámicas y Gamificación para Ferias

Este sistema incluye mecánicas especiales diseñadas para **ferias presiográficas** y eventos con público:

### 🏆 Premio al Último Boleto (Asiento #40)

El participante que compre el **último asiento disponible** (asiento #40) ganará un **premio especial**:

**¿Qué incluye el premio?**
- 👑 **Título de "Rey/Reina del Último Asiento"** con certificado digital de lujo
- 📸 **Sesión de fotos VIP** con los organizadores para redes sociales
- 🌟 **Reconocimiento en pantalla gigante** y en todas las redes oficiales del evento
- 🎨 **Pack digital premium** con 100+ plantillas, mockups y recursos de diseño
- 🎪 **Acceso VIP prioritario** a todas las conferencias con asiento en primera fila
- 📜 **Tu nombre en el Salón de la Fama** del evento (permanente)

**Cómo funciona:**
1. El sistema detecta automáticamente cuando se vende el asiento #40
2. Muestra un mensaje especial: **"🎊 ¡FELICIDADES! Has ganado el GRAN PREMIO por comprar el último boleto"**
3. El terminal que realizó la compra queda registrado
4. El participante debe acercarse al stand principal con su comprobante

### 🎯 Otras Dinámicas Activas

#### 🔢 Boletos de la Suerte (Números Especiales)
Ciertos números de asiento tienen premios sorpresa:

| Asiento | Premio | Descripción |
|---------|--------|-------------|
| #7 | 🍀 **Premio de la Suerte** | Certificado "Afortunado del Día" + Badge especial + Shoutout en redes |
| #13 | 😈 **Premio Supersticioso** | Diploma "Desafiador del Destino" + Pin exclusivo + Tu historia publicada |
| #20 | 🎯 **Premio a la Mitad** | Certificado "Maestro del Equilibrio" + Tu firma en el banner oficial |
| #33 | 🎵 **Premio Triple** | 3 certificados digitales + Título honorífico + 3 menciones en stories |
| #40 | 🏆 **GRAN PREMIO** | Package completo VIP (ver arriba) |

#### ⚡ Velocidad de Compra
- **Primeros 5 boletos:** Cada comprador recibe un **certificado "Early Bird Legendario"** y participación en rifa especial
- **Compra en menos de 10 segundos:** Título de **"Rayo del Diseño"** + Badge digital + GIF animado

#### 🎨 Terminal con Más Ventas
El terminal que venda más boletos durante el evento gana:
- 🥇 **1er lugar:** Trofeo físico "Terminal del Año" + Placa conmemorativa + Hall of Fame
- 🥈 **2do lugar:** Medalla de plata + Certificados para el equipo + Sesión de fotos oficial
- 🥉 **3er lugar:** Medalla de bronce + Reconocimiento en ceremonia de cierre

#### 💫 Bonificación de Grupo
Si **5 personas** compran consecutivamente desde el mismo terminal:
- Título de grupo: **"Los 5 Fantásticos"** con certificado grupal
- Foto grupal en **photobooth premium**
- Video boomerang para redes sociales
- Entrada al **Hall of Fame** del evento
- Participación en el **"Squad Challenge"** del próximo evento

### 📊 Panel de Estadísticas en Tiempo Real

Durante la feria se muestra en pantalla grande:
- ✅ Boletos vendidos vs disponibles
- 🏃 Terminal líder en ventas
- ⏰ Tiempo promedio de compra
- 🎯 Próximos premios disponibles
- 🔥 "Quedan solo X boletos" (alerta cuando hay menos de 10)

### 🎪 Modo Feria Activado

**Características especiales para eventos:**
- 🔊 **Alertas sonoras** cuando quedan 10, 5 y 1 boleto
- 🌈 **Confetti animado** cuando alguien gana un premio
- 📢 **Notificaciones push** a todos los terminales sobre premios ganados
- 🎬 **Replay de compras ganadoras** en video wall
- 📱 **Códigos QR dinámicos** para compartir en redes sociales

### 🎁 Cómo Reclamar tu Premio

1. **Identifica tu premio:** Verifica en pantalla qué premios has ganado
2. **Acude al stand:** Dirígete al mostrador principal con tu dispositivo
3. **Muestra tu comprobante:** El asiento que compraste y el terminal usado
4. **Recibe tu premio:** ¡Disfruta de tu recompensa!
5. **Comparte en redes:** Usa el hashtag **#FeriaPresiografica2026** 📸

### 💡 Consejo para Organizadores

Para maximizar la emoción durante el evento:
- Anuncia cada premio ganado por micrófono
- Muestra el conteo regresivo en pantallas
- Reproduce música/efectos cuando alguien gana
- Toma fotos de los ganadores para el muro de la feria

---

## �🏗️ Arquitectura

### Backend (Spring Boot)
- **Modelo**: `Asiento.java` - Entidad con número, estado y terminal que lo vendió
- **Servicio**: `AsientoService.java` - Lógica de negocio sincronizada
- **Controladores**:
  - `AsientoApiController.java` - API REST
  - `TerminalController.java` - Renderizado de vistas

### Frontend
- `terminal.html` - Interfaz web con Thymeleaf y Bootstrap 5

### API REST Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/asientos` | Obtiene la lista de todos los asientos |
| POST | `/api/comprar-aleatorio?terminalId=X` | Compra el siguiente asiento disponible |
| POST | `/api/comprar-especifico?terminalId=X&numeroAsiento=Y` | Compra un asiento específico |

## 🚀 Cómo ejecutar el proyecto

### ⚡ Inicio Rápido (Linux actual)

**Requisitos previos:**
- **Java 17 o superior** ✅ (tienes Java 25 instalado)
- **Maven 3.6+** ✅ (instalado)

**Ejecutar:**
```bash
cd /home/deepdevjose/Downloads/javainge
mvn clean package -DskipTests
java -jar target/venta-boletos-1.0.0.jar
```

### 📖 Instalación en Otras Plataformas

Para instrucciones detalladas de instalación en **Windows**, **macOS**, **Linux RHEL** o **Linux Debian**, consulta:

👉 **[INSTALL.md - Guía Completa de Instalación](INSTALL.md)**

Incluye instalación de Java y Maven para:
- 🪟 Windows (con y sin Chocolatey)
- 🍎 macOS (con Homebrew)
- 🐧 Linux RHEL/CentOS/Fedora/Rocky/AlmaLinux
- 🐧 Linux Debian/Ubuntu/Mint/Pop!_OS

## 🌐 Acceder a la aplicación

Una vez iniciada la aplicación, accede a:

- **Terminal 1**: http://localhost:8080/terminal/Terminal-1
- **Terminal 2**: http://localhost:8080/terminal/Terminal-2
- **Terminal 3**: http://localhost:8080/terminal/Terminal-3
- **Terminal N**: http://localhost:8080/terminal/Terminal-N

### Probar concurrencia
1. Abre **múltiples pestañas** del navegador
2. Accede a diferentes terminales en cada pestaña

### 🧪 Guía de Pruebas Completa

Para verificar el correcto funcionamiento del sistema y pruebas de concurrencia, consulta:

👉 **[TESTING.md - Guía de Pruebas](TESTING.md)**
3. Intenta comprar asientos al mismo tiempo
4. Observa cómo el sistema previene ventas duplicadas

## 📁 Estructura del proyecto

```
javainge/
├── pom.xml                          # Configuración de Maven
├── src/
│   └── main/
│       ├── java/
│       │   └── com/itat/ventaboletos/
│       │       ├── VentaBoletosApplication.java    # Clase principal
│       │       ├── controller/
│       │       │   ├── AsientoApiController.java   # API REST
│       │       │   └── TerminalController.java     # Controlador web
│       │       ├── model/
│       │       │   └── Asiento.java                # Modelo de datos
│       │       └── service/
│       │           └── AsientoService.java         # Lógica de negocio
│       └── resources/
│           ├── application.properties              # Configuración
│           └── templates/
│               └── terminal.html                   # Vista web
└── target/
    └── venta-boletos-1.0.0.jar                    # JAR compilado
```

## 🛠️ Funcionalidades de la interfaz

### Compra aleatoria
- Haz clic en el botón **"Comprar aleatorio"**
- Se asignará el siguiente asiento disponible

### Compra específica
- Haz clic sobre un asiento **verde (disponible)**
- El asiento se marcará como vendido con el ID de tu terminal

### Indicadores visuales
- 🟩 **Verde**: Asiento disponible
- 🟥 **Rojo**: Asiento vendido (muestra el terminal que lo compró)

## 🔧 Detener la aplicación

```bash
# Encontrar el proceso
ps aux | grep venta-boletos

# Detener (usa el PID que aparece)
kill <PID>

# O con pkill
pkill -f venta-boletos
```
 con inline JavaScript

## 🔧 Bugs Corregidos

### ✅ Terminal ID siempre mostraba "Terminal-1"
**Problema:** Al comprar desde Terminal-2 o Terminal-3, siempre decía "Vendido por Terminal-1"

**Solución:** Se agregó `th:inline="javascript"` en el tag `<script>` para que Thymeleaf procese correctamente la variable `terminalId`

**Ver detalles:** [TESTING.md](TESTING.md)
## 📝 Notas técnicas

- **Puerto**: 8080 (configurable en `application.properties`)
- **Sincronización**: Métodos `synchronized` previenen condiciones de carrera
- **Auto-refresco**: Polling cada 5 segundos para actualizar el estado
- **Thymeleaf**: Motor de plantillas para renderizado server-side con inline JavaScript

### 🎯 Implementación de Dinámicas (Para Desarrolladores)

Las dinámicas descritas arriba son **conceptuales** y están listas para implementarse. Para activarlas:

**Backend (AsientoService.java):**
```java
// Detectar último boleto
if (vendidos == totalAsientos) {
    return new ResultadoCompra(true, "GRAN_PREMIO", numero, 
                               "¡FELICIDADES! Has ganado el GRAN PREMIO");
}

// Detectar boletos especiales
if (numero == 7 || numero == 13 || numero == 20 || numero == 33) {
    // Retornar mensaje especial con el premio
}
```

**Frontend (terminal.html):**
```javascript
// Mostrar confetti y mensaje especial
if (result.mensaje === "GRAN_PREMIO") {
    mostrarConfetti();
    reproducirSonido();
    mostrarModalPremio();
}
```

**Próximas mejoras sugeridas:**
- WebSocket para notificaciones en tiempo real (en lugar de polling)
- Base de datos para persistencia (actualmente en memoria)
- Panel de administración para configurar premios
- API de estadísticas en tiempo real
- Integración con impresoras para tickets físicos
- **Thymeleaf**: Motor de plantillas para renderizado server-side

## 🐛 Solución de problemas

### Puerto 8080 ya en uso
```bash
# Encontrar qué usa el puerto
sudo lsof -i :8080

# Cambiar el puerto en application.properties
server.port=8081
```

### Error de compilación
```bash
mvn clean install -U
```

### Logs
Los logs se muestran en la consola donde ejecutas la aplicación.

---

**Desarrollado con Spring Boot 3.2.0 + Java 25 + Maven 3.9.11**
