# 🧪 Guía de Pruebas - Verificar Corrección del Bug

## 🐛 Bug Corregido

**Problema original:** Cuando comprabas un boleto desde Terminal-2 o Terminal-3, siempre mostraba "Vendido por Terminal-1".

**Solución aplicada:** Se agregó el atributo `th:inline="javascript"` en el tag `<script>` para que Thymeleaf procese correctamente la variable `terminalId`.

---

## ✅ Cómo Verificar que el Bug está Corregido

### Paso 1: Abrir múltiples terminales

Abre **3 pestañas** en tu navegador con estos URLs:

1. **Pestaña 1**: http://localhost:8080/terminal/Terminal-1
2. **Pestaña 2**: http://localhost:8080/terminal/Terminal-2  
3. **Pestaña 3**: http://localhost:8080/terminal/Terminal-3

### Paso 2: Verificar el ID de cada terminal

En cada pestaña, verifica que el título muestre el terminal correcto:
- Pestaña 1 debe mostrar: **"Terminal Terminal-1"**
- Pestaña 2 debe mostrar: **"Terminal Terminal-2"**
- Pestaña 3 debe mostrar: **"Terminal Terminal-3"**

### Paso 3: Comprar asientos desde diferentes terminales

1. En la **Pestaña 1 (Terminal-1)**: Compra el asiento #1
   - Debe mostrar: ✅ "¡Compra exitosa! Asiento 1"
   - El asiento debe aparecer en ROJO con: "Vendido por Terminal-1"

2. En la **Pestaña 2 (Terminal-2)**: Compra el asiento #2
   - Debe mostrar: ✅ "¡Compra exitosa! Asiento 2"
   - El asiento debe aparecer en ROJO con: **"Vendido por Terminal-2"** ← ¡IMPORTANTE!

3. En la **Pestaña 3 (Terminal-3)**: Compra el asiento #3
   - Debe mostrar: ✅ "¡Compra exitosa! Asiento 3"
   - El asiento debe aparecer en ROJO con: **"Vendido por Terminal-3"** ← ¡IMPORTANTE!

### Paso 4: Verificar la sincronización

1. Actualiza las 3 pestañas (F5)
2. En **todas las pestañas** deberías ver:
   - Asiento 1: "Vendido por Terminal-1"
   - Asiento 2: "Vendido por Terminal-2"
   - Asiento 3: "Vendido por Terminal-3"

---

## 🎯 Prueba de Compra Aleatoria

1. En **Terminal-1**: Haz clic en "Comprar aleatorio"
   - Verifica que el asiento comprado muestre: "Vendido por Terminal-1"

2. En **Terminal-2**: Haz clic en "Comprar aleatorio"
   - Verifica que el asiento comprado muestre: "Vendido por Terminal-2"

3. En **Terminal-3**: Haz clic en "Comprar aleatorio"
   - Verifica que el asiento comprado muestre: "Vendido por Terminal-3"

---

## 🔍 Prueba de Concurrencia

### Escenario: Dos terminales intentan comprar el mismo asiento

1. Abre **Terminal-1** y **Terminal-2** en pestañas separadas
2. En **ambas pestañas**, intenta hacer clic en el **mismo asiento libre** (ej: asiento #10) **al mismo tiempo**
3. **Resultado esperado:**
   - ✅ Solo **UNA** terminal logrará comprarlo
   - ❌ La otra recibirá: "No se pudo comprar el asiento 10: OCUPADO"
   - El asiento solo debe mostrar el ID del terminal que ganó

---

## 📊 Checklist de Verificación

Marca cada punto después de verificarlo:

- [ ] Terminal-1 muestra correctamente "Terminal-1" en el título
- [ ] Terminal-2 muestra correctamente "Terminal-2" en el título
- [ ] Terminal-3 muestra correctamente "Terminal-3" en el título
- [ ] Asiento comprado desde Terminal-1 muestra "Vendido por Terminal-1"
- [ ] Asiento comprado desde Terminal-2 muestra "Vendido por Terminal-2"
- [ ] Asiento comprado desde Terminal-3 muestra "Vendido por Terminal-3"
- [ ] Compra aleatoria desde Terminal-2 NO muestra "Terminal-1"
- [ ] Compra aleatoria desde Terminal-3 NO muestra "Terminal-1"
- [ ] Sincronización funciona: dos terminales no pueden comprar el mismo asiento
- [ ] Auto-actualización (cada 5 seg) muestra los cambios en todas las pestañas

---

## 🛠️ Si el Bug Persiste

Si después de seguir estos pasos aún ves "Terminal-1" en todas las compras:

### 1. Verifica que la aplicación se recompiló
```bash
cd /home/deepdevjose/Downloads/javainge
mvn clean package -DskipTests
```

### 2. Reinicia la aplicación
```bash
# Detener
pkill -f venta-boletos

# Iniciar
java -jar target/venta-boletos-1.0.0.jar
```

### 3. Limpia el caché del navegador

**Google Chrome / Edge:**
- Presiona `Ctrl + Shift + Delete` (Windows/Linux) o `Cmd + Shift + Delete` (Mac)
- Selecciona "Imágenes y archivos en caché"
- Haz clic en "Borrar datos"

**Firefox:**
- Presiona `Ctrl + Shift + Delete`
- Selecciona "Caché"
- Haz clic en "Limpiar ahora"

**Safari:**
- Menú Safari → Preferencias → Avanzado
- Marca "Mostrar el menú Desarrollo"
- Menú Desarrollo → Vaciar cachés

### 4. Usa modo incógnito/privado

Abre las pestañas en modo incógnito para evitar problemas de caché:
- **Chrome/Edge**: `Ctrl + Shift + N`
- **Firefox**: `Ctrl + Shift + P`
- **Safari**: `Cmd + Shift + N`

### 5. Verifica el código fuente en el navegador

1. Abre http://localhost:8080/terminal/Terminal-2
2. Presiona `F12` para abrir DevTools
3. Ve a la pestaña "Console"
4. Escribe: `console.log(terminalId)`
5. Debería mostrar: `"Terminal-2"`

Si muestra `"Terminal-1"`, el archivo HTML no se recompiló correctamente.

---

## 📝 Detalles Técnicos del Fix

**Archivo modificado:** `src/main/resources/templates/terminal.html`

**Cambio realizado:**
```html
<!-- ANTES (Bug) -->
<script>
    const terminalId = /*[[${terminalId}]]*/ 'Terminal-1';
    ...
</script>

<!-- DESPUÉS (Fix) -->
<script th:inline="javascript">
    const terminalId = /*[[${terminalId}]]*/ 'Terminal-1';
    ...
</script>
```

**Explicación:**
- El atributo `th:inline="javascript"` le indica a Thymeleaf que procese las expresiones dentro del bloque `<script>`
- Sin este atributo, Thymeleaf puede no reemplazar correctamente el valor de `${terminalId}`
- Esto causaba que siempre usara el valor por defecto: `'Terminal-1'`

---

## ✨ Resultado Esperado Final

Después de aplicar el fix:

| Terminal | Asiento Comprado | Mensaje Mostrado |
|----------|------------------|------------------|
| Terminal-1 | Asiento #5 | "Vendido por Terminal-1" ✅ |
| Terminal-2 | Asiento #10 | "Vendido por Terminal-2" ✅ |
| Terminal-3 | Asiento #15 | "Vendido por Terminal-3" ✅ |

¡Ahora cada terminal muestra su propio identificador correctamente! 🎉
