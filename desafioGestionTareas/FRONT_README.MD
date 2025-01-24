# Desafío Previred FrontEnd:
```
create by:
                name = "Rene Angel Silva Zuñiga"
                email= "reneangel.silva@gmail.com"
```

# Proyecto de Agenda de Contactos

## Introduccion al proyecto.

El objetivo de esté proyecto consiste en cubrir las necesidades del desafio presentado por la empresa previred
y cubrir la mayor cantidad de caracteristicas en un tiempo limite inpuesto por el mismo proceso en cuestion.

Todo el proceso de desarrollo de la solucion apunta a crear un MPV que cubra la mayor cantidad de caracteristicas sin compromente su funcionalidades.

---

## Proyecto Creado con las siguientes tecnologias:

```
   @ant-design/icons": "^5.6.0",
    "antd": "^5.23.2",
    "jquery": "^3.7.1",
    "react": "^18.3.1",
    "react-dom": "^18.3.1"
```


### Patrones de Diseños Utilizados

- Atomic Design Pattern https://medium.com/@janelle.wg/atomic-design-pattern-how-to-structure-your-react-application-2bb4d9ca5f97



Este proyecto es una aplicación de agenda de contactos desarrollada con React y TypeScript. Permite a los usuarios ver, agregar y eliminar contactos utilizando una API REST.

## Instalación

Para instalar y ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    cd <NOMBRE_DEL_REPOSITORIO>
    ```

2. Instala las dependencias:
    ```sh
    npm install
    ```

3. Inicia el servidor:
    ```sh
    npm run start-server
    ```

4. Inicia la aplicación:
    ```sh
    npm run dev
    ```

La aplicación estará disponible en [http://localhost:9000](http://localhost:9000).

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

```
.
├── .gitignore
├── db.json
├── server.js
├── package.json
├── README.md
└── desafio-react-agenda/
    ├── .gitignore
    ├── eslint.config.js
    ├── index.html
    ├── package.json
    ├── public/
    ├── README.md
    ├── src/
    │   ├── App.css
    │   ├── App.tsx
    │   ├── assets/
    │   ├── components/
    │   │   ├── atomic/
    │   │   │   ├── atoms/
    │   │   │   ├── molecules/
    │   │   │   ├── organisms/
    │   │   │   ├── pages/
    │   │   │   ├── templates/
    │   │   ├── hoc/
    │   ├── index.css
    │   ├── main.tsx
    │   ├── services/
    │   │   ├── agendaServices/
    │   │   │   ├── useDeleteUser.ts
    │   │   │   ├── useGetUser.ts
    │   │   │   ├── usePostUser.ts
    │   ├── vite-env.d.ts
    ├── tsconfig.app.json
    ├── tsconfig.json
    ├── tsconfig.node.json
    ├── vite.config.ts
```

## Descripción de la API

La API REST proporciona los siguientes endpoints:

- `GET /api/users`: Obtiene la lista de usuarios.
- `GET /api/users/:id`: Obtiene un usuario por su ID.
- `POST /api/users`: Crea un nuevo usuario.
- `DELETE /api/users/:id`: Elimina un usuario por su ID.

## Componentes Principales

### `AgendaPreviredPage`

Este componente es la página principal de la aplicación. Contiene la tabla de contactos y el formulario para agregar nuevos contactos.

### `TableAgenda`

Este componente muestra la lista de contactos en una tabla. Permite buscar y eliminar contactos.

### `DrawerAgenda`

Este componente es un formulario en un drawer que permite agregar nuevos contactos.

## Servicios

### `useGetUser`

Este servicio obtiene la lista de usuarios desde la API.

### `usePostUser`

Este servicio crea un nuevo usuario en la API.

### `useDeleteUser`

Este servicio elimina un usuario por su ID desde la API.

## Configuración de ESLint

El proyecto utiliza ESLint para mantener la calidad del código. La configuración se encuentra en el archivo `eslint.config.js`.

```js
import js from '@eslint/js'
import globals from 'globals'
import reactHooks from 'eslint-plugin-react-hooks'
import reactRefresh from 'eslint-plugin-react-refresh'
import tseslint from 'typescript-eslint'

export default tseslint.config(
  { ignores: ['dist'] },
  {
    extends: [js.configs.recommended, ...tseslint.configs.recommended],
    files: ['**/*.{ts,tsx}'],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
    },
    plugins: {
      'react-hooks': reactHooks,
      'react-refresh': reactRefresh,
    },
    rules: {
      ...reactHooks.configs.recommended.rules,
      'react-refresh/only-export-components': [
        'warn',
        { allowConstantExport: true },
      ],
    },
  },
)
```

## Agradecimientos

Se agradece a todas las partes que ayudaron en la creacion de el proyecto.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.


#### FIN DOCUMENTACION...