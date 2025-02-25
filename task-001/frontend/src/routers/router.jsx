import { createBrowserRouter } from "react-router-dom";
import Login from "../pages/Login";
import MainLayout from "../layout/MainLayout";
import Overview from "../pages/Overview";
import Prescription from "../pages/Prescription";
import CreatePrescription from "../pages/CreatePrescription";
import PrivateRoute from "./PrivateRoute";
import PrescriptionDetails from "../pages/PrescriptionDetails";
import EditPrescription from "../components/EditPrescription";
import Register from "../pages/Register";
import Post from "../pages/Post";

export const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <PrivateRoute>
        <MainLayout />
      </PrivateRoute>
    ),
    children: [
      {
        path: "/",
        element: <Prescription />,
      },
      {
        path: "/overview",
        element: <Overview />,
      },
      {
        path: "/prescription/:id",
        element: <PrescriptionDetails />,
        loader: ({ params }) =>
          fetch(`http://localhost:8080/api/v1/prescription/${params.id}`),
      },
      {
        path: "/edit-prescription/:id",
        element: <EditPrescription />,
        loader: ({ params }) =>
          fetch(`http://localhost:8080/api/v1/prescription/${params.id}`),
      },
      {
        path: "/create-prescription",
        element: <CreatePrescription />,
      },
      {
        path: "/post",
        element: <Post />,
      },
    ],
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <Register />,
  },
]);
