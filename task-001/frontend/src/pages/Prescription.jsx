/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function Prescription() {
  const [prescriptions, setPrescriptions] = useState([]);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);
  const [noData, setNoData] = useState(false);

  // Filter states
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  // Convert date to milliseconds
  const convertToMilliseconds = (date) => {
    return new Date(date).getTime(); // Returns millisecos
  };

  // Get today's date in milliseconds
  const getTodayInMilliseconds = () => {
    const today = new Date();
    return convertToMilliseconds(today);
  };

  // Get the start of the current month
  const getStartOfMonthInMilliseconds = () => {
    const today = new Date();
    const startOfMonth = new Date(
      Date.UTC(today.getFullYear(), today.getMonth(), 1)
    );
    return startOfMonth.getTime();
  };

  // Set default values for startDate and endDate
  useEffect(() => {
    setStartDate(getStartOfMonthInMilliseconds());
    setEndDate(getTodayInMilliseconds());
  }, []);

  // Fetch prescriptions
  useEffect(() => {
    setLoading(true);
    setNoData(false);

    axios
      .get(`http://localhost:8080/api/v1/prescription`, {
        params: {
          page,
          size: 10,
          startDate: startDate ? convertToMilliseconds(startDate) : null,
          endDate: endDate ? convertToMilliseconds(endDate) : null,
        },
      })
      .then((res) => {
        setPrescriptions(res.data.content);
        setNoData(res.data.content.length === 0);
        setTotalPages(res.data.totalPages);
      })
      .catch((error) => {
        console.error("Error fetching prescriptions:", error);
      })
      .finally(() => setLoading(false));
  }, [page, startDate, endDate]);

  // Pagination handlers
  const handlePrevPage = () => {
    if (page > 1) setPage((prev) => prev - 1);
  };

  const handleNextPage = () => {
    if (page < totalPages) setPage((prev) => prev + 1);
  };

  // Reset filters and trigger the API call
  const resetFilters = () => {
    setStartDate(getStartOfMonthInMilliseconds());
    setEndDate(getTodayInMilliseconds());
    setPage(1);
  };

  // Handle change
  const handleStartDateChange = (e) => {
    setStartDate(e.target.value);
    setPage(1);
  };

  const handleEndDateChange = (e) => {
    setEndDate(e.target.value);
    setPage(1);
  };

  return (
    <div className="p-6">
      <h2 className="text-6xl text-center bg-blue-500 py-6 text-white rounded-lg font-bold mb-4">
        All Prescriptions
      </h2>

      {/* Filter */}
      <div className="flex flex-wrap gap-4 items-center justify-center mb-4">
        <div className="flex flex-col">
          <label className="text-sm text-gray-600 mb-1">From</label>
          <input
            type="date"
            value={
              startDate ? new Date(startDate).toISOString().split("T")[0] : ""
            }
            onChange={handleStartDateChange}
            className="border border-gray-300 p-2 rounded"
          />
        </div>
        <div className="flex flex-col">
          <label className="text-sm text-gray-600 mb-1">To</label>
          <input
            type="date"
            value={endDate ? new Date(endDate).toISOString().split("T")[0] : ""}
            onChange={handleEndDateChange}
            className="border border-gray-300 p-2 rounded"
          />
        </div>

        <button
          onClick={resetFilters}
          className="ml-auto bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
        >
          Reset
        </button>
      </div>

      {/* Loading and No Data Handling */}
      {loading ? (
        <div className="flex justify-center items-center h-[80vh]">
          <div className="w-16 h-16 border-4 border-dashed rounded-full animate-spin dark:border-blue-500"></div>
        </div>
      ) : noData ? (
        <div className="text-center text-4xl text-red-500 my-32">
          No prescriptions found.
        </div>
      ) : (
        <>
          {/* Table */}
          <table className="min-w-full bg-white border border-gray-300">
            <thead className="bg-gray-100">
              <tr className="text-center">
                <th className="py-2 px-4 border-b-2 uppercase text-left text-sm font-semibold">
                  No
                </th>
                <th className="py-2 px-4 border-b-2 uppercase text-sm font-semibold">
                  Patient Name
                </th>
                <th className="py-2 px-4 border-b-2 uppercase text-sm font-semibold">
                  Patient Age
                </th>
                <th className="py-2 px-4 border-b-2 uppercase text-sm font-semibold">
                  Patient Gender
                </th>
                <th className="py-2 px-4 border-b-2 uppercase text-sm font-semibold">
                  Prescription Date
                </th>
                <th className="py-2 px-4 border-b-2 uppercase text-sm font-semibold">
                  Actions
                </th>
              </tr>
            </thead>
            <tbody>
              {prescriptions.map((prescription, index) => (
                <tr
                  key={prescription.id}
                  className={`text-center ${
                    index % 2 === 0 ? "bg-gray-50" : "bg-white"
                  }`}
                >
                  <td className="py-3 px-4 text-left border-b text-gray-700">
                    {index + 1 + (page - 1) * 10}
                  </td>
                  <td className="py-3 px-4 capitalize border-b text-gray-700">
                    {prescription.name}
                  </td>
                  <td className="py-3 px-4 border-b text-gray-700">
                    {prescription.age}
                  </td>
                  <td className="py-3 px-4 capitalize border-b text-gray-700">
                    {prescription.gender}
                  </td>
                  <td className="py-3 px-4 border-b text-gray-700">
                    {new Date(
                      prescription.prescriptionDate
                    ).toLocaleDateString()}
                  </td>
                  <td className="py-3 px-4 border-b">
                    <Link
                      to={`/prescription/${prescription.id}`}
                      className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-1 px-4 rounded"
                    >
                      View Details
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* Pagination controls */}
          <div className="flex justify-between items-center mt-6">
            <button
              onClick={handlePrevPage}
              disabled={page === 1}
              className={`${
                page === 1 ? "bg-gray-300" : "bg-blue-500 hover:bg-blue-600"
              } text-white font-bold py-2 px-4 rounded`}
            >
              Previous
            </button>
            <span className="text-sm font-medium text-gray-700">
              Page {page} of {totalPages}
            </span>
            <button
              onClick={handleNextPage}
              disabled={page === totalPages}
              className={`${
                page === totalPages
                  ? "bg-gray-300"
                  : "bg-blue-500 hover:bg-blue-600"
              } text-white font-bold py-2 px-4 rounded`}
            >
              Next
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default Prescription;
