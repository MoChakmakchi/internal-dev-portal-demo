import {
  createTheme,
  lightTheme,
  darkTheme,
  genPageTheme,
  shapes,
} from '@backstage/theme';

export const myTheme = createTheme({
  palette: {
    ...darkTheme.palette,
    primary: {
      main: '#FFD900',
    },
    secondary: {
      main: '#FFD900',
    },
  },
  defaultPageTheme: 'home',
  pageTheme: {
    home: genPageTheme({
      colors: ['#004FB6', '#fff'],
      shape: shapes.wave,
    }),
    // documentation: genPageTheme({
    //   colors: ['#FFD900', '#fff'],
    //   shape: shapes.wave2,
    // }),
    // tool: genPageTheme({ colors: ['#FFD900', '#fff'], shape: shapes.round }),
    // service: genPageTheme({
    //   colors: ['#FFD900', '#fff'],
    //   shape: shapes.wave,
    // }),
    // website: genPageTheme({
    //   colors: ['#FFD900', '#fff'],
    //   shape: shapes.wave,
    // }),
    // library: genPageTheme({
    //   colors: ['#FFD900', '#fff'],
    //   shape: shapes.wave,
    // }),
    // other: genPageTheme({ colors: ['#FFD900', '#fff'], shape: shapes.wave }),
    // app: genPageTheme({ colors: ['#FFD900', '#fff'], shape: shapes.wave }),
    // apis: genPageTheme({ colors: ['#FFD900', '#fff'], shape: shapes.wave }),
  },
});
