# SOLID Principles - Java

## Single Responsibility Principle (SRP)

### Definición

> "Una clase debe tener una única razón para cambiar."

Cada clase debe encargarse de una sola cosa. Si una clase tiene más de una responsabilidad, un cambio en una de ellas puede afectar involuntariamente a las demás.

---

### Problema Original

La clase `UserManager` original violaba el SRP porque tenía **tres responsabilidades distintas**:

| Responsabilidad | Método involucrado |
|---|---|
| Validar datos del usuario | `isValidEmail`, `isValidPassword` |
| Guardar en base de datos | `saveToDatabase` |
| Enviar notificaciones | `sendWelcomeEmail` |

Esto significa que si se cambiaba el formato de validación, la forma de guardar datos, o el canal de notificación, la clase completa tenía que modificarse — violando el principio de "una sola razón para cambiar".

---

### Solución: Refactorización con SRP

Se dividió la clase original en **cuatro clases independientes**, cada una con una única responsabilidad:

```
src/main/java/srp/
├── UserValidator.java       → Valida email y contraseña
├── UserRepository.java      → Persiste los datos del usuario
├── NotificationService.java → Envía notificaciones (email de bienvenida)
└── UserManager.java         → Coordina el flujo de registro
```

#### `UserValidator`
Solo se encarga de validar que el email tenga formato correcto y que la contraseña tenga al menos 8 caracteres.

#### `UserRepository`
Solo se encarga de guardar el usuario en la base de datos.

#### `NotificationService`
Solo se encarga de enviar comunicaciones al usuario (correo de bienvenida).

#### `UserManager`
Coordina las tres clases anteriores para ejecutar el flujo completo de registro. Si el flujo cambia (por ejemplo, agregar un paso de logging), solo esta clase se modifica.

---

### Cómo ejecutar

```bash
mvn compile exec:java -Dexec.mainClass=srp.Main
```

---

### Reflexión

Aplicar SRP hizo que el código fuera más fácil de mantener y de probar. Antes, si queríamos cambiar cómo se envían los correos (por ejemplo, pasar de email a SMS), teníamos que modificar `UserManager` completo, con riesgo de romper la validación o la persistencia.

Después de la refactorización, ese cambio solo afecta a `NotificationService`. Cada clase tiene una razón clara para existir y una razón clara para cambiar.

**El principio más retador** fue entender dónde trazar la línea entre responsabilidades. Por ejemplo, ¿`UserManager` debería validar por su cuenta o delegar completamente? SRP responde que debe delegar: su única responsabilidad es *coordinar*, no *ejecutar* cada paso.

---

## Open/Closed Principle (OCP)

### Definición

> "Una clase debe estar abierta para la extensión, pero cerrada para la modificación."

Se puede agregar nuevo comportamiento sin cambiar el código que ya funciona.

---

### Problema Inicial

El servicio de notificaciones original usaba sentencias `if-else` para determinar qué tipo de notificación enviar. Para agregar Fax o WhatsApp había que modificar `NotificationService`, con riesgo de romper lo que ya funcionaba.

---

### Solución: Refactorización con OCP

```
src/main/java/ocp/
├── Notification.java         → Interfaz con contrato send(String message)
├── EmailNotification.java    → Implementación para Email
├── SMSNotification.java      → Implementación para SMS
├── PushNotification.java     → Implementación para Push
├── FaxNotification.java      → Extensión sin modificar nada existente
├── NotificationService.java  → Usa polimorfismo, cerrada a modificación
└── Main.java                 → Prueba todos los tipos de notificación
```

`NotificationService` recibe cualquier objeto que implemente `Notification`. Para agregar un nuevo canal (ej. WhatsApp) basta con crear una nueva clase — sin tocar `NotificationService`.

---

### Cómo ejecutar

```bash
mvn compile exec:java -Dexec.mainClass=ocp.Main
```

---

### Reflexión

Aplicar OCP transformó un código rígido en una arquitectura flexible. La interfaz `Notification` actúa como contrato: cualquier nueva implementación se integra sin riesgo de regresión. La clave fue reemplazar la lógica condicional por polimorfismo, dejando `NotificationService` estable mientras el sistema crece.

*Nota: Revisa la carpeta de "Screenshots" para ver las capturas de la ejecución exitosa de los distintos tipos de notificación.*

---

## Liskov Substitution Principle (LSP)

### Definición

> "Los objetos de una clase derivada deben poder reemplazar a los objetos de la clase base sin alterar el comportamiento correcto del programa."

Si `S` es un subtipo de `T`, cualquier lugar donde se use `T` debe funcionar correctamente con `S`.

---

### Problema que resuelve

Sin LSP, una subclase puede heredar métodos que no tiene sentido que implemente, obligando a lanzar excepciones o dejar comportamientos vacíos. Por ejemplo, si `Animal` tuviera el método `walk()`, un `Fish` heredaría algo que no puede hacer — rompiendo el contrato de la clase base.

---

### Solución Aplicada con LSP

```
src/main/java/lsp/
├── Animal.java     → Clase base abstracta con makeSound() (común a todos)
├── Walkable.java   → Interfaz solo para animales que pueden caminar
├── Dog.java        → Extiende Animal e implementa Walkable
├── Fish.java       → Extiende Animal (sin Walkable, porque no camina)
└── Main.java       → Demuestra sustitución correcta de la clase base
```

`Dog` puede sustituir a `Animal` en cualquier contexto sin sorpresas. `Fish` también, pero el compilador impide tratarlo como `Walkable` — el error ocurre en compilación, no en ejecución.

---

### Cómo ejecutar

```bash
mvn compile exec:java -Dexec.mainClass=lsp.Main
```

---

### Reflexión

LSP nos enseña que la herencia no es solo reutilización de código: es un contrato de comportamiento. Separar capacidades opcionales en interfaces (`Walkable`) en lugar de meterlas en la clase base evita que las subclases hereden responsabilidades que no pueden cumplir. El compilador se convierte en un aliado que detecta violaciones antes de que lleguen a producción.
