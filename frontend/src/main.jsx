import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App";
import { ThemeProvider, createTheme, CssBaseline } from "@mui/material";

const theme = createTheme({
    palette: {
        mode: "dark",
        primary: {
            main: "#f2aac2",
        },
        background: {
            default: "#050525",
            paper: "#040419",
        },
    },
    shape: {
        borderRadius: 15,
    },
    h3: {
        fontWeight: 700,
        letterSpacing: "0.04em",
    },
    typography: {
        fontFamily: `"Roboto", "Helvetica", "Arial", sans-serif`,
        h3: {
            fontFamily: `"Playfair Display", serif`,
            fontWeight: 700,
        },
        h5: {
            fontFamily: `"Playfair Display", serif`,
            fontWeight: 600,
        },
        h6: {
            fontFamily: `"Playfair Display", serif`,
            fontWeight: 600,
        },
    },
});

createRoot(document.getElementById("root")).render(
    <StrictMode>
        <ThemeProvider theme={theme}>
            <CssBaseline />
            <App />
        </ThemeProvider>
    </StrictMode>
);
