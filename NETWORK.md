# 🌐 Guía de Acceso en Red - Sistema de Venta de Boletos

Esta guía explica cómo acceder a la aplicación desde otros dispositivos en tu red local (LAN).

---

## 📡 Configuración Actual

### Tu Servidor
- **IP Local:** `192.168.100.98`
- **Puerto:** `8080`
- **Sistema:** Fedora Linux

### Acceso desde esta máquina (localhost)
- http://localhost:8080/terminal/Terminal-1
- http://localhost:8080/terminal/Terminal-2
- http://localhost:8080/terminal/Terminal-3

### ✅ Acceso desde otros dispositivos en la red
- http://192.168.100.98:8080/terminal/Terminal-1
- http://192.168.100.98:8080/terminal/Terminal-2
- http://192.168.100.98:8080/terminal/Terminal-3

---

## ⚙️ Cambio Realizado

Se agregó la siguiente línea en `application.properties`:

```properties
server.address=0.0.0.0
```

**Significado:**
- `0.0.0.0` = Escuchar en todas las interfaces de red
- Permite conexiones desde localhost Y desde otros dispositivos en la red
- Spring Boot usa **Tomcat embebido** (ya incluido, no necesitas instalarlo)

---

## 🔥 Configuración del Firewall

### Fedora/RHEL/CentOS (firewalld)

**Abrir puerto 8080:**
```bash
sudo firewall-cmd --add-port=8080/tcp --permanent
sudo firewall-cmd --reload
```

**Verificar puertos abiertos:**
```bash
sudo firewall-cmd --list-ports
```

**Verificar estado del firewall:**
```bash
sudo firewall-cmd --state
```

### Ubuntu/Debian (ufw)

**Abrir puerto 8080:**
```bash
sudo ufw allow 8080/tcp
sudo ufw reload
```

**Verificar estado:**
```bash
sudo ufw status
```

### Windows Firewall

1. Panel de Control → Sistema y seguridad → Firewall de Windows Defender
2. Configuración avanzada → Reglas de entrada
3. Nueva regla → Puerto → TCP → Puerto específico: 8080
4. Permitir la conexión → Aplicar a todos los perfiles
5. Nombre: "Spring Boot App - Puerto 8080"

---

## 🚀 Cómo Arrancar el Servidor en tu Sistema Operativo

### 🪟 Windows

#### Opción 1: Desde CMD (Command Prompt)
```cmd
# 1. Navegar al directorio del proyecto
cd C:\ruta\al\proyecto\javainge

# 2. Arrancar el servidor
java -jar target\venta-boletos-1.0.0.jar
```

#### Opción 2: Desde PowerShell
```powershell
# 1. Navegar al directorio del proyecto
cd C:\ruta\al\proyecto\javainge

# 2. Arrancar el servidor
java -jar .\target\venta-boletos-1.0.0.jar
```

#### Opción 3: Hacer doble clic
1. Abre el Explorador de archivos
2. Navega a la carpeta `javainge\target`
3. Haz doble clic en `venta-boletos-1.0.0.jar`
4. Se abrirá una ventana de consola negra (no la cierres)

#### Opción 4: Crear acceso directo
```cmd
# Crear archivo start-server.bat
@echo off
cd /d C:\ruta\al\proyecto\javainge
java -jar target\venta-boletos-1.0.0.jar
pause
```
Guarda como `start-server.bat` y haz doble clic para ejecutar.

**Detener el servidor en Windows:**
- Presiona `Ctrl + C` en la ventana de CMD/PowerShell
- O cierra la ventana de consola

**Obtener tu IP en Windows:**
```cmd
ipconfig
# Busca "Dirección IPv4" en la sección de tu adaptador WiFi/Ethernet
```

---

### 🍎 macOS

#### Opción 1: Desde Terminal
```bash
# 1. Navegar al directorio del proyecto
cd ~/ruta/al/proyecto/javainge

# 2. Arrancar el servidor
java -jar target/venta-boletos-1.0.0.jar
```

#### Opción 2: Ejecutar en segundo plano
```bash
# Arrancar en background
cd ~/ruta/al/proyecto/javainge
nohup java -jar target/venta-boletos-1.0.0.jar > server.log 2>&1 &

# Ver el PID del proceso
echo $!

# Ver logs en tiempo real
tail -f server.log
```

#### Opción 3: Crear script de arranque
```bash
# Crear archivo start-server.sh
cat > start-server.sh << 'EOF'
#!/bin/bash
cd ~/ruta/al/proyecto/javainge
echo "🚀 Iniciando servidor..."
java -jar target/venta-boletos-1.0.0.jar
EOF

# Dar permisos de ejecución
chmod +x start-server.sh

# Ejecutar
./start-server.sh
```

**Detener el servidor en macOS:**
```bash
# Opción 1: Si está en terminal, presiona Ctrl + C

# Opción 2: Buscar y matar el proceso
ps aux | grep venta-boletos
kill <PID>

# Opción 3: Usar pkill
pkill -f venta-boletos
```

**Obtener tu IP en macOS:**
```bash
ipconfig getifaddr en0  # WiFi
# o
ifconfig | grep "inet " | grep -v 127.0.0.1
```

---

### 🐧 Linux (Fedora/RHEL/CentOS/Ubuntu/Debian)

#### Opción 1: Ejecutar en terminal (primer plano)
```bash
# 1. Navegar al directorio del proyecto
cd ~/Downloads/javainge

# 2. Arrancar el servidor
java -jar target/venta-boletos-1.0.0.jar
```

#### Opción 2: Ejecutar en segundo plano con nohup
```bash
# Arrancar en background
cd ~/Downloads/javainge
nohup java -jar target/venta-boletos-1.0.0.jar > server.log 2>&1 &

# Guardar el PID
echo $! > server.pid

# Ver logs en tiempo real
tail -f server.log
```

#### Opción 3: Ejecutar con systemd (servicio del sistema)
```bash
# Crear archivo de servicio
sudo nano /etc/systemd/system/venta-boletos.service
```

Contenido del archivo:
```ini
[Unit]
Description=Sistema de Venta de Boletos
After=network.target

[Service]
Type=simple
User=deepdevjose
WorkingDirectory=/home/deepdevjose/Downloads/javainge
ExecStart=/usr/bin/java -jar /home/deepdevjose/Downloads/javainge/target/venta-boletos-1.0.0.jar
Restart=on-failure
RestartSec=10
StandardOutput=journal
StandardError=journal

[Install]
WantedBy=multi-user.target
```

Comandos para usar el servicio:
```bash
# Recargar systemd
sudo systemctl daemon-reload

# Habilitar inicio automático
sudo systemctl enable venta-boletos

# Iniciar el servicio
sudo systemctl start venta-boletos

# Ver estado
sudo systemctl status venta-boletos

# Ver logs
sudo journalctl -u venta-boletos -f

# Detener el servicio
sudo systemctl stop venta-boletos

# Reiniciar el servicio
sudo systemctl restart venta-boletos
```

#### Opción 4: Crear script de arranque simple
```bash
# Crear archivo start-server.sh
cat > start-server.sh << 'EOF'
#!/bin/bash
cd ~/Downloads/javainge
echo "🚀 Iniciando servidor de venta de boletos..."
echo "📡 IP Local: $(hostname -I | awk '{print $1}')"
echo "🌐 Accede desde: http://$(hostname -I | awk '{print $1}'):8080/terminal/Terminal-1"
echo ""
java -jar target/venta-boletos-1.0.0.jar
EOF

# Dar permisos
chmod +x start-server.sh

# Ejecutar
./start-server.sh
```

**Detener el servidor en Linux:**
```bash
# Opción 1: Si está en terminal, presiona Ctrl + C

# Opción 2: Buscar proceso y matar
ps aux | grep venta-boletos
kill <PID>

# Opción 3: Usar pkill (más rápido)
pkill -f venta-boletos

# Opción 4: Matar con fuerza si no responde
pkill -9 -f venta-boletos

# Opción 5: Si usas systemd
sudo systemctl stop venta-boletos
```

**Obtener tu IP en Linux:**
```bash
# Método 1
hostname -I | awk '{print $1}'

# Método 2
ip addr show | grep "inet " | grep -v 127.0.0.1 | awk '{print $2}' | cut -d/ -f1

# Método 3
ifconfig | grep "inet " | grep -v 127.0.0.1
```

---

## 🔄 Reiniciar el Servidor (Todos los Sistemas Operativos)

A veces necesitas reiniciar el servidor después de hacer cambios:

### Windows
```cmd
# Detener (Ctrl + C) y volver a ejecutar
java -jar target\venta-boletos-1.0.0.jar
```

### macOS / Linux
```bash
# Detener
pkill -f venta-boletos

# Esperar un momento
sleep 2

# Iniciar de nuevo
java -jar target/venta-boletos-1.0.0.jar
```

### Script de reinicio rápido (Linux/macOS)
```bash
# Crear restart-server.sh
cat > restart-server.sh << 'EOF'
#!/bin/bash
echo "🔄 Reiniciando servidor..."
pkill -f venta-boletos
sleep 2
cd ~/Downloads/javainge
java -jar target/venta-boletos-1.0.0.jar
EOF

chmod +x restart-server.sh
./restart-server.sh
```

---

## 🧪 Verificar que funciona

### 1. Verifica que el servidor está escuchando en todas las interfaces

```bash
# En Linux/Mac
ss -tlnp | grep 8080
# Debe mostrar: 0.0.0.0:8080 (no solo 127.0.0.1:8080)

# O con netstat
netstat -tlnp | grep 8080
```

**En Windows:**
```cmd
netstat -an | findstr :8080
```

**Resultado esperado:**
```
0.0.0.0:8080  (escucha en todas las interfaces) ✅
```

**Resultado incorrecto:**
```
127.0.0.1:8080  (solo localhost) ❌
```

### 2. Obtén tu IP local

**Linux/Mac:**
```bash
hostname -I | awk '{print $1}'
# o
ip addr show | grep "inet " | grep -v 127.0.0.1
```

**Windows:**
```cmd
ipconfig
# Busca "Dirección IPv4"
```

**Tu IP actual:** `192.168.100.98`

### 3. Prueba desde otro dispositivo

Desde tu teléfono, tablet u otra computadora en la **misma red WiFi**:

1. Abre el navegador
2. Ve a: `http://192.168.100.98:8080/terminal/Terminal-1`
3. Deberías ver la interfaz de compra de boletos

---

## 📱 Acceso desde Dispositivos Móviles

### Desde tu teléfono/tablet (mismo WiFi)

1. Conecta tu dispositivo al **mismo WiFi** que tu servidor
2. Abre Chrome/Safari
3. Navega a:
   - `http://192.168.100.98:8080/terminal/Terminal-1`
   - `http://192.168.100.98:8080/terminal/Terminal-2`
   - `http://192.168.100.98:8080/terminal/Terminal-3`

### Prueba de terminales múltiples en red

**Escenario realista:**
- Computadora 1: Terminal-1
- Computadora 2: Terminal-2
- Teléfono 1: Terminal-3
- Tablet: Terminal-4

Todos pueden comprar simultáneamente y ver las actualizaciones sincronizadas.

---

## 🔒 Seguridad

### ⚠️ IMPORTANTE: Acceso solo en Red Local (LAN)

Esta configuración expone la aplicación en tu **red local solamente**:
- ✅ Dispositivos en tu WiFi/Red local pueden acceder
- ❌ Internet externo NO puede acceder (protegido por tu router)

### Para exponer a Internet (NO recomendado sin seguridad)

Si realmente necesitas acceso desde Internet, considera:

1. **Configurar router (Port Forwarding)**
   - Requiere acceso al router
   - Reenviar puerto 8080 a 192.168.100.98:8080
   - Usar IP pública del router

2. **Usar servicios de túnel (Desarrollo)**
   - ngrok: `ngrok http 8080`
   - localtonet
   - serveo

3. **Desplegar en la nube (Producción)**
   - AWS EC2 / Lightsail
   - Google Cloud
   - Azure
   - Heroku
   - Railway

⚠️ **Si expones a Internet, implementa:**
- Autenticación de usuarios
- HTTPS/SSL
- Rate limiting
- CORS configurado
- Auditoría de seguridad

---

## 🛠️ Solución de Problemas

### ❌ No puedo acceder desde otro dispositivo

**1. Verifica que ambos dispositivos están en la misma red**
```bash
# En el servidor
hostname -I

# En el otro dispositivo (Windows)
ipconfig

# Ambos deben tener IPs en el mismo rango
# Ejemplo: 192.168.100.x o 192.168.1.x
```

**2. Verifica el firewall**
```bash
# Linux
sudo firewall-cmd --list-ports  # Debe incluir 8080/tcp

# O temporalmente desactivar (SOLO PARA PRUEBA)
sudo systemctl stop firewalld
# Probar acceso
# Si funciona, el problema es el firewall
sudo systemctl start firewalld
```

**3. Verifica que el servidor escucha en 0.0.0.0**
```bash
ss -tlnp | grep 8080
# Debe mostrar: 0.0.0.0:8080
```

Si muestra `127.0.0.1:8080`, revisa que `application.properties` tenga:
```properties
server.address=0.0.0.0
```

**4. Ping al servidor**
```bash
# Desde el otro dispositivo
ping 192.168.100.98

# Si no responde, hay problema de red/firewall
```

**5. Verifica que la aplicación está corriendo**
```bash
ps aux | grep venta-boletos
# o
curl http://localhost:8080/api/asientos
```

### ❌ Funciona en LAN pero quiero acceder desde fuera

Necesitas configurar **Port Forwarding** en tu router:

1. Accede a tu router (generalmente 192.168.1.1 o 192.168.0.1)
2. Busca "Port Forwarding" o "Reenvío de puertos"
3. Configura:
   - Puerto externo: 8080
   - Puerto interno: 8080
   - IP interna: 192.168.100.98
   - Protocolo: TCP
4. Obtén tu IP pública: https://whatismyipaddress.com
5. Accede desde Internet: `http://TU_IP_PUBLICA:8080/terminal/Terminal-1`

⚠️ **Advertencia:** Esto expone tu aplicación a Internet sin seguridad.

---

## 📊 Verificación Completa

### Checklist de Red

- [ ] Aplicación recompilada con `server.address=0.0.0.0`
- [ ] Aplicación corriendo (puerto 8080)
- [ ] Firewall configurado (puerto 8080/tcp abierto)
- [ ] IP local identificada (192.168.100.98)
- [ ] Acceso desde localhost funciona
- [ ] Acceso desde otro dispositivo en la red funciona
- [ ] Múltiples terminales pueden conectarse simultáneamente

### Comando de Verificación Rápida

```bash
# Ejecutar en el servidor
echo "=== VERIFICACIÓN DE RED ==="
echo "IP Local: $(hostname -I | awk '{print $1}')"
echo "Puerto: 8080"
echo ""
echo "Servidor escuchando en:"
ss -tlnp 2>/dev/null | grep 8080 | awk '{print $4}' || netstat -tlnp 2>/dev/null | grep 8080
echo ""
echo "Puertos del firewall:"
sudo firewall-cmd --list-ports 2>/dev/null || echo "ufw: $(sudo ufw status 2>/dev/null | grep 8080)"
echo ""
echo "Acceso desde red:"
echo "  http://$(hostname -I | awk '{print $1}'):8080/terminal/Terminal-1"
```

---

## 🎯 URLs de Acceso

### Desde el Servidor (localhost)
```
http://localhost:8080/terminal/Terminal-1
http://localhost:8080/terminal/Terminal-2
http://localhost:8080/terminal/Terminal-3
```

### Desde la Red Local (LAN)
```
http://192.168.100.98:8080/terminal/Terminal-1
http://192.168.100.98:8080/terminal/Terminal-2
http://192.168.100.98:8080/terminal/Terminal-3
```

### API REST (Para desarrollo)
```
GET  http://192.168.100.98:8080/api/asientos
POST http://192.168.100.98:8080/api/comprar-aleatorio?terminalId=Terminal-X
POST http://192.168.100.98:8080/api/comprar-especifico?terminalId=Terminal-X&numeroAsiento=Y
```

---

## 📝 Notas Técnicas

### ¿Por qué Spring Boot y no Tomcat externo?

**Spring Boot incluye Tomcat embebido:**
- ✅ No necesitas instalar Tomcat por separado
- ✅ Todo está en el JAR (aplicación autocontenida)
- ✅ Más fácil de desplegar y mantener
- ✅ Configuración más simple
- ✅ Producción-ready

**Si necesitaras Tomcat externo:**
- Cambiar empaquetado a WAR en `pom.xml`
- Extender `SpringBootServletInitializer`
- Desplegar WAR en servidor Tomcat
- Configurar Tomcat para escuchar en 0.0.0.0

**Para este proyecto, Tomcat embebido es suficiente y recomendado.**

---

## 🚀 Comandos de Inicio Rápido por Sistema Operativo

### Windows
```cmd
# Iniciar servidor
cd C:\ruta\al\proyecto\javainge
java -jar target\venta-boletos-1.0.0.jar

# Obtener IP
ipconfig

# Detener servidor
Ctrl + C
```

### macOS
```bash
# Iniciar servidor
cd ~/ruta/al/proyecto/javainge
java -jar target/venta-boletos-1.0.0.jar

# Obtener IP
ipconfig getifaddr en0

# Detener servidor
Ctrl + C  o  pkill -f venta-boletos
```

### Linux
```bash
# Iniciar servidor
cd ~/Downloads/javainge
java -jar target/venta-boletos-1.0.0.jar

# Obtener IP
hostname -I | awk '{print $1}'

# Detener servidor
Ctrl + C  o  pkill -f venta-boletos
```

---

## 📋 Tabla de Referencia Rápida

| Acción | Windows | macOS | Linux |
|--------|---------|-------|-------|
| **Navegar al proyecto** | `cd C:\ruta\javainge` | `cd ~/ruta/javainge` | `cd ~/Downloads/javainge` |
| **Iniciar servidor** | `java -jar target\venta-boletos-1.0.0.jar` | `java -jar target/venta-boletos-1.0.0.jar` | `java -jar target/venta-boletos-1.0.0.jar` |
| **Detener servidor** | `Ctrl + C` | `Ctrl + C` o `pkill -f venta-boletos` | `Ctrl + C` o `pkill -f venta-boletos` |
| **Ver IP local** | `ipconfig` | `ipconfig getifaddr en0` | `hostname -I` |
| **Ver puerto activo** | `netstat -an \| findstr :8080` | `lsof -i :8080` | `ss -tlnp \| grep 8080` |
| **Firewall abrir puerto** | GUI (ver arriba) | `sudo ufw allow 8080/tcp` (si usa ufw) | `sudo firewall-cmd --add-port=8080/tcp --permanent` |
| **Ejecutar en background** | No recomendado | `nohup java -jar target/venta-boletos-1.0.0.jar &` | `nohup java -jar target/venta-boletos-1.0.0.jar &` |
| **Crear servicio** | No aplica | No aplica | `sudo systemctl enable venta-boletos` |

---

## 📚 Documentación Relacionada

- [README.md](README.md) - Información general
- [INSTALL.md](INSTALL.md) - Instalación multiplataforma
- [TESTING.md](TESTING.md) - Guía de pruebas
- [CHANGES.md](CHANGES.md) - Registro de cambios

---

**Configurado para:** Red Local (LAN)  
**Tomcat:** Embebido en Spring Boot ✅  
**Puerto:** 8080  
**IP del Servidor:** 192.168.100.98  
**Última actualización:** 10 de Marzo, 2026
