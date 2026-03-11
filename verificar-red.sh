#!/bin/bash
# Script de verificación de acceso en red
# Uso: ./verificar-red.sh

echo "═══════════════════════════════════════════════════════"
echo "  🌐 VERIFICACIÓN DE ACCESO EN RED"
echo "═══════════════════════════════════════════════════════"
echo ""

# 1. Verificar IP local
echo "📡 1. IP Local del Servidor:"
IP_LOCAL=$(hostname -I | awk '{print $1}')
echo "   → $IP_LOCAL"
echo ""

# 2. Verificar puerto
echo "🔌 2. Puerto de la Aplicación:"
echo "   → 8080"
echo ""

# 3. Verificar que el servidor está escuchando
echo "👂 3. Estado del Servidor:"
if ss -tlnp 2>/dev/null | grep -q ":8080"; then
    BIND_ADDRESS=$(ss -tlnp 2>/dev/null | grep ":8080" | awk '{print $4}')
    echo "   → ✅ Servidor CORRIENDO"
    echo "   → Escuchando en: $BIND_ADDRESS"
    
    if echo "$BIND_ADDRESS" | grep -q "^\*:8080"; then
        echo "   → ✅ Accesible desde la RED (0.0.0.0)"
    elif echo "$BIND_ADDRESS" | grep -q "^127\.0\.0\.1:8080"; then
        echo "   → ⚠️  Solo accesible desde LOCALHOST"
        echo "   → Necesitas configurar server.address=0.0.0.0"
    fi
else
    echo "   → ❌ Servidor NO está corriendo en puerto 8080"
    echo "   → Inicia con: java -jar target/venta-boletos-1.0.0.jar"
fi
echo ""

# 4. Verificar firewall (Fedora/RHEL)
echo "🔥 4. Estado del Firewall:"
if command -v firewall-cmd &> /dev/null; then
    if sudo firewall-cmd --list-ports 2>/dev/null | grep -q "8080/tcp"; then
        echo "   → ✅ Puerto 8080/tcp ABIERTO"
    else
        echo "   → ⚠️  Puerto 8080/tcp NO está abierto"
        echo "   → Abre con: sudo firewall-cmd --add-port=8080/tcp --permanent"
        echo "   →           sudo firewall-cmd --reload"
    fi
# Verificar ufw (Ubuntu/Debian)
elif command -v ufw &> /dev/null; then
    if sudo ufw status 2>/dev/null | grep -q "8080.*ALLOW"; then
        echo "   → ✅ Puerto 8080 PERMITIDO en ufw"
    else
        echo "   → ⚠️  Puerto 8080 no configurado en ufw"
        echo "   → Abre con: sudo ufw allow 8080/tcp"
    fi
else
    echo "   → ℹ️  Firewall no detectado o desactivado"
fi
echo ""

# 5. URLs de acceso
echo "🌐 5. URLs de Acceso:"
echo ""
echo "   📍 Desde este equipo (localhost):"
echo "      http://localhost:8080/terminal/Terminal-1"
echo "      http://localhost:8080/terminal/Terminal-2"
echo "      http://localhost:8080/terminal/Terminal-3"
echo ""
echo "   📍 Desde otros dispositivos en la RED:"
echo "      http://$IP_LOCAL:8080/terminal/Terminal-1"
echo "      http://$IP_LOCAL:8080/terminal/Terminal-2"
echo "      http://$IP_LOCAL:8080/terminal/Terminal-3"
echo ""

# 6. Prueba rápida
echo "🧪 6. Prueba Rápida:"
if curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/api/asientos 2>/dev/null | grep -q "200"; then
    echo "   → ✅ API REST funcionando correctamente"
else
    echo "   → ⚠️  API REST no responde"
fi
echo ""

# 7. Información de red
echo "📊 7. Información de Red:"
echo "   → Dispositivos deben estar en la misma red WiFi/LAN"
echo "   → Rango de red: $(echo $IP_LOCAL | cut -d'.' -f1-3).x"
echo ""

# 8. Códigos QR (si qrencode está instalado)
if command -v qrencode &> /dev/null; then
    echo "📱 8. Código QR para acceso móvil:"
    echo "   http://$IP_LOCAL:8080/terminal/Terminal-1" | qrencode -t UTF8
    echo ""
else
    echo "💡 Tip: Instala qrencode para generar códigos QR:"
    echo "   sudo dnf install qrencode  # Fedora/RHEL"
    echo "   sudo apt install qrencode  # Ubuntu/Debian"
    echo ""
fi

echo "═══════════════════════════════════════════════════════"
echo "  ✅ Verificación completada"
echo "═══════════════════════════════════════════════════════"
echo ""
echo "📖 Para más información, consulta: NETWORK.md"
