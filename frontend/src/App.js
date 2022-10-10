import {Navigate, Route, Routes} from "react-router-dom";
import {SnackbarProvider} from "notistack";
import FlatsCatalogPage from "./pages/flats-catalog-page";

function AppWrapper() {
    return (
        <SnackbarProvider maxSnack={3}>
            <Routes>
                <Route path="/catalog" element={<FlatsCatalogPage/>}/>
                <Route
                    path="*"
                    element={<Navigate to="/catalog" replace />}
                />
            </Routes>
        </SnackbarProvider>
    );
}

export default AppWrapper;
