import React from "react";

/*
 cp. https://tailwindui.com/components/application-ui/layout/panels
 */

export const Card = ({ header, children }) => (
  <>
    {header != null ? (
      <div className="bg-white overflow-hidden shadow rounded-lg divide-y divide-gray-200">
        <div className="px-4 py-5 sm:px-6">{header}</div>
        <div className="px-4 py-5 sm:p-6">{children}</div>
      </div>
    ) : (
      <div className="bg-white overflow-hidden shadow rounded-lg">
        <div className="px-4 py-5 sm:p-6">{children}</div>
      </div>
    )}
  </>
);
