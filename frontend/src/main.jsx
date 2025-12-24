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
            default: "#1a1a2e",
            paper: "#16213e",
        },
    },
    shape: {
        borderRadius: 15,
    },
    h3: {
        fontWeight: 700,
        letterSpacing: "0.04em",
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
