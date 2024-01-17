/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {},
    colors: {
      primary: {
        100: '#dbac3d',
        200: '#e1b555',
        300: '#e6be6a',
        400: '#ebc780',
        500: '#f0d095',
        600: '#f4d9aa'
      },
      dark: {
        100: '#121212',
        200: '#282828',
        300: '#3f3f3f',
        400: '#575757',
        500: '#717171',
        600: '#8b8b8b',
        700: '#a3a3a3',
        800: '#b5b5b5',
        900: '#c7c7c7'
      },
      black: {
        alpha: {
          100: '#00000010',
          200: '#00000020',
          300: '#00000030',
          400: '#00000040',
          500: '#00000050',
          600: '#00000060',
          700: '#00000070',
          800: '#00000080',
          900: '#00000090'
        }
      },
      mixed: {
        100: '#241f18',
        200: '#39342d',
        300: '#4e4a44',
        400: '#65615c',
        500: '#7d7975',
        600: '#96938f'
      }
    }
  },
  plugins: [],
}